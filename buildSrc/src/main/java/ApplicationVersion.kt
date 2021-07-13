internal data class ApplicationVersion(
    private val major: Int,
    private val minor: Int,
    private val hotfix: Int,
    private val revision: Int
) {
    val code = (major * 1_000_000) + (minor * 10_000) + (hotfix * 100) + revision
    val name = "$major.$minor.$hotfix.$revision"
}