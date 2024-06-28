package club.libridge.libridgebackend.core

import org.scalatest.flatspec.AnyFlatSpec
import club.libridge.libridgebackend.core.HandScala
import org.junit.jupiter.api.Test

@Test
class HandScalaTest extends AnyFlatSpec {
  "A HandScala" should "have a size" in {
    val handScala = new HandScala("86.KT2.K85.Q9742")
    assert(handScala.size === 13)
  }
  "A HandScala" should "be constructed using a pbnString" in {
    new HandScala("86.KT2.K85.Q9742")
  }
}
