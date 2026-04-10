package com.hotel.demo.facade;

import com.hotel.demo.model.AdditionalService;
import com.hotel.demo.model.Invoice;
import com.hotel.demo.model.Reservation;
import com.hotel.demo.model.Room;
import com.hotel.demo.repository.InMemoryDatabase;
import com.hotel.demo.service.*;
import com.hotel.demo.util.UUIDGenerator;
import org.springframework.stereotype.Component;

@Component
public class HotelFacade {

    private final RoomService roomService;
    private final PricingService pricingService;
    private final AdditionalServiceService additionalServiceService;
    private final BillingService billingService;
    private final AccessService accessService;
    private final InMemoryDatabase database;

    public HotelFacade(RoomService roomService,
                       PricingService pricingService,
                       AdditionalServiceService additionalServiceService,
                       BillingService billingService,
                       AccessService accessService,
                       InMemoryDatabase database) {
        this.roomService = roomService;
        this.pricingService = pricingService;
        this.additionalServiceService = additionalServiceService;
        this.billingService = billingService;
        this.accessService = accessService;
        this.database = database;
    }

    /**
     * Crea una nueva reserva.
     *
     * @param customerName Nombre del huésped
     * @param roomType     Tipo de habitación: "SINGLE", "DOUBLE", "SUITE"
     * @param nights       Número de noches
     * @return Reservation creada con estado "CREATED"
     */
    public Reservation crearReserva(String customerName, String roomType, int nights) {
        Room room = roomService.getAvailableRoom(roomType);
        String id = UUIDGenerator.generate();
        String accessKey = accessService.generateKey();
        Reservation reservation = new Reservation(id, customerName, room, nights, accessKey);
        database.save(reservation);
        return reservation;
    }

    /**
     * Agrega un servicio adicional a una reserva existente.
     *
     * @param reservationId ID de la reserva
     * @param tipoServicio  "spa", "breakfast" o "transport"
     * @return Reservation actualizada
     */
    public Reservation agregarServicio(String reservationId, String tipoServicio) {
        Reservation reservation = findReservationOrThrow(reservationId);
        AdditionalService service = additionalServiceService.createService(tipoServicio);
        reservation.addService(service);
        return reservation;
    }

    /**
     * Realiza el check-in de una reserva (CREATED → CHECKED_IN).
     *
     * @param reservationId ID de la reserva
     * @return Reservation con estado "CHECKED_IN"
     */
    public Reservation realizarCheckIn(String reservationId) {
        Reservation reservation = findReservationOrThrow(reservationId);
        reservation.checkIn();
        return reservation;
    }

    /**
     * Realiza el check-out de una reserva (CHECKED_IN → CHECKED_OUT)
     * y libera la habitación.
     *
     * @param reservationId ID de la reserva
     * @return Reservation con estado "CHECKED_OUT"
     */
    public Reservation realizarCheckOut(String reservationId) {
        Reservation reservation = findReservationOrThrow(reservationId);
        reservation.checkOut();
        roomService.releaseRoom(reservation.getRoom());
        return reservation;
    }

    /**
     * Genera la factura final de una reserva.
     *
     * @param reservationId ID de la reserva
     * @return Invoice con desglose de costos
     */
    public Invoice generarFactura(String reservationId) {
        Reservation reservation = findReservationOrThrow(reservationId);
        return billingService.generateInvoice(reservation);
    }

    /**
     * Genera la factura indicando si es temporada alta.
     */
    public Invoice generarFactura(String reservationId, boolean highSeason) {
        Reservation reservation = findReservationOrThrow(reservationId);
        return billingService.generateInvoice(reservation, highSeason);
    }

    /**
     * Consulta disponibilidad de habitaciones por tipo.
     */
    public long consultarDisponibilidad(String roomType) {
        return roomService.getAllRooms().stream()
                .filter(r -> r.getType().equalsIgnoreCase(roomType) && r.isAvailable())
                .count();
    }

    /**
     * Obtiene una reserva por ID.
     */
    public Reservation obtenerReserva(String reservationId) {
        return findReservationOrThrow(reservationId);
    }

    // ── Helper privado ──────────────────────────────────────────────────────────

    private Reservation findReservationOrThrow(String reservationId) {
        return database.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Reserva no encontrada con ID: " + reservationId));
    }
}