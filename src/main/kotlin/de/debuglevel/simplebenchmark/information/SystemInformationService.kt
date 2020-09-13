package de.debuglevel.simplebenchmark.information

import com.fasterxml.jackson.databind.ObjectMapper
import mu.KotlinLogging
import oshi.SystemInfo
import javax.inject.Singleton


@Singleton
class SystemInformationService {
    private val logger = KotlinLogging.logger {}

    private val ignoredThings: Map<Class<out Any>, Set<String>>
        get() {
            return mapOf(
//            OperatingSystem::class.java to setOf(
//                OperatingSystem::getBitness.name
//            ),
            )
        }

    fun getAllSystemInformationJson(): String {
        logger.debug { "Getting SystemInformation as JSON..." }

        val mapper = ObjectMapper()
        mapper.setAnnotationIntrospector(IgnoranceIntrospector(ignoredThings))

        val systemInfo = SystemInfo()
        val json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(systemInfo)

        logger.debug { "Got SystemInformation as JSON (${json.count()} bytes)" }
        return json
    }

    fun getSystemInformation(): Map<String, Any> {
        val information = mutableMapOf<String, Any>()

        information["ComputerIdentifier"] = ComputerID.computerIdentifier

        val systemInfo = SystemInfo()

        val operatingSystem = systemInfo.operatingSystem
        information["OperatingSystemFamily"] = operatingSystem.family
        information["OperatingSystemBitness"] = operatingSystem.bitness
        information["OperatingSystemManufacturer"] = operatingSystem.manufacturer
        information["OperatingSystemBuildNumber"] = operatingSystem.versionInfo.buildNumber
        //information["OperatingSystemCodeName"] = operatingSystem.versionInfo.codeName
        information["OperatingSystemVersion"] = operatingSystem.versionInfo.version
        information["OperatingSystemProcessCount"] = operatingSystem.processCount

        val hardwareAbstractionLayer = systemInfo.hardware
        information["HalSystemModel"] = hardwareAbstractionLayer.computerSystem.model
        information["HalSystemSerialNumber"] = hardwareAbstractionLayer.computerSystem.serialNumber
        information["HalSystemManufacturer"] = hardwareAbstractionLayer.computerSystem.manufacturer

        information["HalMemoryTotal"] =
            hardwareAbstractionLayer.memory.total / 1024.0 / 1024.0 / 1024.0 // report as GiB
        information["HalMemoryAvailable"] =
            hardwareAbstractionLayer.memory.available / 1024.0 / 1024.0 / 1024.0  // report as GiB

        information["HalProcessorMaximumFrequency"] =
            hardwareAbstractionLayer.processor.maxFreq / 1000.0 / 1000.0 / 1000.0 // report as GHz
        information["HalProcessorLogicalProcessorCount"] = hardwareAbstractionLayer.processor.logicalProcessorCount
        information["HalProcessorPhysicalPackageCount"] =
            hardwareAbstractionLayer.processor.physicalPackageCount // might be sockets?
        information["HalProcessorPhysicalProcessorCount"] =
            hardwareAbstractionLayer.processor.physicalProcessorCount // might be cores?

        return information
    }
}