package de.debuglevel.simplebenchmark.systeminformation

import oshi.SystemInfo

/**
 * MIT License
 *
 * Copyright (c) 2010 - 2020 The OSHI Project Contributors: https://github.com/oshi/oshi/graphs/contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */


/**
 * Attempts to create a unique computer identifier. Note that serial numbers
 * won't work on Linux without user cooperation.
 */
object ComputerID {
    /**
     * Generates a Computer Identifier, which may be part of a strategy to construct
     * a licence key. (The identifier may not be unique as in one case hashcode
     * could be same for multiple values, and the result may differ based on whether
     * the program is running with sudo/root permission.) The identifier string is
     * based upon the processor serial number, vendor, processor identifier, and
     * total processor count.
     *
     * @return A string containing four hyphen-delimited fields representing the
     * processor; the first 3 are 32-bit hexadecimal values and the last one
     * is an integer value.
     */
    val computerIdentifier: String
        get() {
            val systemInfo = SystemInfo()
            val vendor = systemInfo.operatingSystem.manufacturer
            val processorSerialNumber = systemInfo.hardware.computerSystem.serialNumber
            val processorIdentifier = systemInfo.hardware.processor.processorIdentifier.identifier
            val processors = systemInfo.hardware.processor.logicalProcessorCount

            val processorIdentifierHash = String.format("%08x", processorIdentifier.hashCode())
            val processorSerialNumberHash = String.format("%08x", processorSerialNumber.hashCode())
            val processorVendorHash = String.format("%08x", vendor.hashCode())

            return "$processorVendorHash-$processorSerialNumberHash-$processorIdentifierHash-$processors"
        }
}