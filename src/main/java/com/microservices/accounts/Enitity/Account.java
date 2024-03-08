package com.microservices.accounts.Enitity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseEntity{
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "account_number")
    @Id
    private Long accountNumber;
    @Column(name="account_type")
    private String accountType;
    @Column(name = "branch_address")
    private String branchAddress;

}
