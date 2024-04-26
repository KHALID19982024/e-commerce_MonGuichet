package org.example.Mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.example.DTO.PaymentDTO;
import org.example.Entity.PaymentEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-21T17:04:00+0100",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class PaymentMapperImpl implements PaymentMapper {

    @Override
    public PaymentEntity toEntity(PaymentDTO payment) {
        if ( payment == null ) {
            return null;
        }

        PaymentEntity.PaymentEntityBuilder<?, ?> paymentEntity = PaymentEntity.builder();

        paymentEntity.id( payment.getId() );
        paymentEntity.customerId( payment.getCustomerId() );
        paymentEntity.orderId( payment.getOrderId() );
        paymentEntity.createAt( payment.getCreateAt() );

        return paymentEntity.build();
    }

    @Override
    public List<PaymentEntity> toListEntity(List<PaymentDTO> paymentDTOList) {
        if ( paymentDTOList == null ) {
            return null;
        }

        List<PaymentEntity> list = new ArrayList<PaymentEntity>( paymentDTOList.size() );
        for ( PaymentDTO paymentDTO : paymentDTOList ) {
            list.add( toEntity( paymentDTO ) );
        }

        return list;
    }

    @Override
    public PaymentDTO toDTO(PaymentEntity entity) {
        if ( entity == null ) {
            return null;
        }

        PaymentDTO paymentDTO = new PaymentDTO();

        paymentDTO.setId( entity.getId() );
        paymentDTO.setCustomerId( entity.getCustomerId() );
        paymentDTO.setOrderId( entity.getOrderId() );
        paymentDTO.setCreateAt( entity.getCreateAt() );

        return paymentDTO;
    }

    @Override
    public List<PaymentDTO> toListDTO(List<PaymentEntity> paymentEntityList) {
        if ( paymentEntityList == null ) {
            return null;
        }

        List<PaymentDTO> list = new ArrayList<PaymentDTO>( paymentEntityList.size() );
        for ( PaymentEntity paymentEntity : paymentEntityList ) {
            list.add( toDTO( paymentEntity ) );
        }

        return list;
    }
}
