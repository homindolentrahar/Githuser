package com.homindolentrahar.githuser.domain.model

data class RepoModel(
    val archived: Boolean,
    val cloneUrl: String,
    val createdAt: String,
    val defaultBranch: String,
    val description: String,
    val disabled: Boolean,
    val fork: Boolean,
    val forks: Int,
    val forksCount: Int,
    val fullName: String,
    val gitUrl: String,
    val homepage: String,
    val htmlUrl: String,
    val id: Int,
    val isTemplate: Boolean,
    val language: String,
    val license: RepoLicenseModel,
    val mirrorUrl: String,
    val name: String,
    val nodeId: String,
    val openIssues: Int,
    val openIssuesCount: Int,
    val pushedAt: String,
    val size: Int,
    val sshUrl: String,
    val stargazersCount: Int,
    val svnUrl: String,
    val topics: List<String>,
    val updatedAt: String,
    val visibility: String,
    val watchers: Int,
    val watchersCount: Int
) {
    companion object {
        fun empty(): RepoModel = RepoModel(
            false,
            "",
            "",
            "",
            "",
            disabled = false,
            fork = false,
            forks = 0,
            forksCount = 0,
            fullName = "",
            "",
            "",
            "",
            0,
            false,
            "",
            RepoLicenseModel.empty(),
            "",
            "",
            "",
            0,
            0,
            "",
            0,
            "",
            0,
            "",
            emptyList(),
            "",
            "",
            0,
            0,
        )
    }
}

data class RepoLicenseModel(
    val key: String,
    val name: String,
    val spdxId: String,
    val nodeId: String
) {
    companion object {
        fun empty(): RepoLicenseModel = RepoLicenseModel(
            key = "",
            name = "",
            spdxId = "",
            nodeId = ""
        )
    }
}
