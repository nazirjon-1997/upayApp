package com.developer.upayonline.models

class ServiceModel {

    var serviceId = 0
    var serviceLang: String? = null
    var serviceName: String? = null
    var labelLang: String? = null
    var labelValue: String? = null
    var accountNumberType: String? = null
    var accountNumberMinLenght = 0
    var accontNumberMaxLenght = 0
    var minPayment = 0.0
    var maxPayment = 0.0
    var commissionType: String? = null
    var commission = 0
    var hasCheck: String? = null

    constructor(
        serviceId: Int,
        serviceLang: String?,
        serviceName: String?,
        labelLang: String?,
        labelValue: String?,
        accountNumberType: String?,
        accountNumberMinLenght: Int,
        accontNumberMaxLenght: Int,
        minPayment: Double,
        maxPayment: Double,
        commissionType: String?,
        commission: Int,
        hasCheck: String?
    ) {
        this.serviceId = serviceId
        this.serviceLang = serviceLang
        this.serviceName = serviceName
        this.labelLang = labelLang
        this.labelValue = labelValue
        this.accountNumberType = accountNumberType
        this.accountNumberMinLenght = accountNumberMinLenght
        this.accontNumberMaxLenght = accontNumberMaxLenght
        this.minPayment = minPayment
        this.maxPayment = maxPayment
        this.commissionType = commissionType
        this.commission = commission
        this.hasCheck = hasCheck
    }
}