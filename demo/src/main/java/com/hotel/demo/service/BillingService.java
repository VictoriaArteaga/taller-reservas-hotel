package com.hotel.demo.service;

import com.hotel.demo.model.AdditionalService;
import com.hotel.demo.model.Invoice;
import com.hotel.demo.model.Reservation;
import org.springframework.stereotype.Service;

@Service
public class BillingService {

    private final PricingService pricingService;

    public BillingService(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    /**
     * Genera la factura final de una reserva.
     *
     * Lógica:
     *  1. Calcula roomCost usando PricingService
     *     (se asume temporada alta si el parámetro highSeason es true)
     *  2. Suma todos los servicios adicionales: servicesCost = sum(service.price)
     *  3. total = roomCost + servicesCost
     *  4. Retorna Invoice
     *
     * Nota: highSeason se puede obtener del contexto o dejarse fijo.
     *       El HotelFacade decidirá si es temporada alta al llamar este método.
     *
     * @param reservation Reserva con habitación y servicios
     * @param highSeason  true si es temporada alta
     * @return Invoice con desglose completo
     */
    public Invoice generateInvoice(Reservation reservation, boolean highSeason) {
        // 1. Costo habitación
        double roomCost = pricingService.calculate(
                reservation.getRoom(),
                reservation.getNights(),
                highSeason
        );

        // 2. Costo servicios adicionales
        double servicesCost = reservation.getServices()
                .stream()
                .mapToDouble(AdditionalService::getPrice)
                .sum();

        // 3. Total
        double total = roomCost + servicesCost;

        // 4. Retornar factura
        return new Invoice(
                reservation.getId(),
                reservation.getCustomerName(),
                reservation.getRoom().getType(),
                reservation.getNights(),
                roomCost,
                reservation.getServices(),
                servicesCost,
                total
        );
    }

    /**
     * Sobrecarga: genera factura asumiendo temporada baja por defecto.
     */
    public Invoice generateInvoice(Reservation reservation) {
        return generateInvoice(reservation, false);
    }
}