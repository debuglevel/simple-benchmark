package de.debuglevel.simplebenchmark.systeminformation

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import io.swagger.v3.oas.annotations.tags.Tag
import mu.KotlinLogging

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/systeminformation")
@Tag(name = "systeminformation")
class SystemInformationController(
    private val systemInformationService: SystemInformationService
) {
    private val logger = KotlinLogging.logger {}

    @Get("/all")
    fun getAll(): HttpResponse<*> {
        logger.debug("Called getAll()")
        return try {
            val systemInformationJson = systemInformationService.getAllSystemInformationJson()

            HttpResponse.ok(systemInformationJson)
        } catch (e: Exception) {
            logger.error(e) { "Unhandled exception" }
            HttpResponse.serverError("Unhandled exception: ${e.message}")
        }
    }

    @Get("/some")
    fun getSome(): HttpResponse<*> {
        logger.debug("Called getAll()")
        return try {
            val systemInformationJson = systemInformationService.getSystemInformation()

            HttpResponse.ok(systemInformationJson)
        } catch (e: Exception) {
            logger.error(e) { "Unhandled exception" }
            HttpResponse.serverError("Unhandled exception: ${e.message}")
        }
    }
}