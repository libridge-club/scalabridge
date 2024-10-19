package scalabridge.nonpure

import org.junit.jupiter.api.Test
import scalabridge.UnitFunSpec

@Test
class PBNReaderTest extends UnitFunSpec {

  describe("A PBNReader") {
      it("should run") {
        PBNReader.readFromFile("Chekback.pbn")
      }
  }
}
