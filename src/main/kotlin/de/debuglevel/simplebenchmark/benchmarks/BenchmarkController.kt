package de.debuglevel.simplebenchmark.benchmarks

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import io.swagger.v3.oas.annotations.tags.Tag
import mu.KotlinLogging

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/benchmarks")
@Tag(name = "benchmarks")
class BenchmarkController(
    private val benchmarkService: BenchmarkService
) {
    private val logger = KotlinLogging.logger {}

    @Get("/")
    fun getAllBenchmarks(): HttpResponse<*> {
        logger.debug("Called getAllBenchmarks()")
        return try {
            val benchmarks = benchmarkService.listAll()
            val getBenchmarkResponses = benchmarks
                .map { GetBenchmarkResponse(it) }

            HttpResponse.ok(getBenchmarkResponses)
        } catch (e: Exception) {
            logger.error(e) { "Unhandled exception" }
            HttpResponse.serverError("Unhandled exception: ${e.message}")
        }
    }

    @Get("/scores/")
    fun getAllScores(): HttpResponse<*> {
        logger.debug("Called getAllScores()")
        return try {
            val benchmarkScores = benchmarkService.runAll()
            val getBenchmarkScoreResponses = benchmarkScores
                .map { GetBenchmarkScoreResponse(it) }

            HttpResponse.ok(getBenchmarkScoreResponses)
        } catch (e: Exception) {
            logger.error(e) { "Unhandled exception" }
            HttpResponse.serverError("Unhandled exception: ${e.message}")
        }
    }
}