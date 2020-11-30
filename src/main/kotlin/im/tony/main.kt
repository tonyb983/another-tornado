package im.tony

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.serialization.ExperimentalSerializationApi
import tornadofx.launch
import kotlin.contracts.ExperimentalContracts
import kotlin.experimental.ExperimentalTypeInference
import kotlin.io.path.ExperimentalPathApi
import kotlin.time.ExperimentalTime

@ExperimentalStdlibApi
@ExperimentalUnsignedTypes
@ExperimentalContracts
@ExperimentalCoroutinesApi
@ExperimentalPathApi
@ExperimentalSerializationApi
@ExperimentalTime
fun main() {
  launch<MyApp>()
}
