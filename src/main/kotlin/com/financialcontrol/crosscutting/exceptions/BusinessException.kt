package com.financialcontrol.crosscutting.exceptions

import java.lang.RuntimeException

open class BusinessException(override val message: String): RuntimeException()