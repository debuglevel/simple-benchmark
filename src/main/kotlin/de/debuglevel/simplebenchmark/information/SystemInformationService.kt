package de.debuglevel.simplebenchmark.information

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import oshi.SystemInfo
import javax.inject.Singleton


@Singleton
class SystemInformationService {
    fun json() {
        // Jackson ObjectMapper
        val mapper = ObjectMapper()

        // Fetch some OSHI objects
        val si = SystemInfo()
        val hal = si.hardware
        try {
            // Pretty print computer system
            println("JSON for CPU:")
            val cpu = hal.processor
            println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cpu))

            // Print memory
            println("JSON for Memory:")
            val mem = hal.memory
            println(mapper.writeValueAsString(mem))
        } catch (e: JsonProcessingException) {
            println("Exception encountered: " + e.message)
        }
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