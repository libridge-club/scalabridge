package club.libridge.libridgebackend.core

case class HandScala(pbnString: String):

  def size: Int =
    pbnString.count(_!='.')

  override def toString: String =
    this.pbnString

end HandScala