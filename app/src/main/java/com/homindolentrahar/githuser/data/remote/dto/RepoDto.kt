package com.homindolentrahar.githuser.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.homindolentrahar.githuser.domain.model.RepoLicenseModel
import com.homindolentrahar.githuser.domain.model.RepoModel

data class RepoDto(
    val archived: Boolean,
    @SerializedName("clone_url")
    val cloneUrl: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("default_branch")
    val defaultBranch: String,
    val description: String?,
    val disabled: Boolean,
    val fork: Boolean,
    val forks: Int,
    @SerializedName("forks_count")
    val forksCount: Int,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("git_url")
    val gitUrl: String,
    val homepage: String?,
    @SerializedName("html_url")
    val htmlUrl: String,
    val id: Int,
    @SerializedName("is_template")
    val isTemplate: Boolean,
    val language: String?,
    val license: RepoLicenseDto?,
    @SerializedName("mirror_url")
    val mirrorUrl: String?,
    val name: String,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("open_issues")
    val openIssues: Int,
    @SerializedName("open_issues_count")
    val openIssuesCount: Int,
    @SerializedName("pushed_at")
    val pushedAt: String?,
    val size: Int,
    @SerializedName("ssh_url")
    val sshUrl: String,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
    @SerializedName("svn_url")
    val svnUrl: String,
    val topics: List<String?>,
    @SerializedName("updated_at")
    val updatedAt: String,
    val visibility: String,
    val watchers: Int,
    @SerializedName("watchers_count")
    val watchersCount: Int
) {
    companion object {
        fun fromModel(model: RepoModel): RepoDto = RepoDto(
            model.archived,
            model.cloneUrl,
            model.createdAt,
            model.defaultBranch,
            model.description,
            model.disabled,
            model.fork,
            model.forks,
            model.forksCount,
            model.fullName,
            model.gitUrl,
            model.homepage,
            model.htmlUrl,
            model.id,
            model.isTemplate,
            model.language,
            RepoLicenseDto.fromModel(model.license),
            model.mirrorUrl,
            model.name,
            model.nodeId,
            model.openIssues,
            model.openIssuesCount,
            model.pushedAt,
            model.size,
            model.sshUrl,
            model.stargazersCount,
            model.svnUrl,
            model.topics,
            model.updatedAt,
            model.visibility,
            model.watchers,
            model.watchersCount
        )
    }

    fun toModel(): RepoModel = RepoModel(
        archived,
        cloneUrl,
        createdAt,
        defaultBranch,
        description ?: "No Description",
        disabled,
        fork,
        forks,
        forksCount,
        fullName,
        gitUrl,
        homepage ?: "No Homepage",
        htmlUrl,
        id,
        isTemplate,
        language ?: "No Language",
        license?.toModel() ?: RepoLicenseModel.empty(),
        mirrorUrl ?: "No Mirror URL",
        name,
        nodeId,
        openIssues,
        openIssuesCount,
        pushedAt ?: "No Pushed At",
        size,
        sshUrl,
        stargazersCount,
        svnUrl,
        topics.map { it ?: "No Topic" },
        updatedAt,
        visibility,
        watchers,
        watchersCount
    )
}

data class RepoLicenseDto(
    val key: String,
    val name: String,
    @SerializedName("spdx_id")
    val spdxId: String,
    @SerializedName("node_id")
    val nodeId: String
) {
    companion object {
        fun fromModel(model: RepoLicenseModel): RepoLicenseDto = RepoLicenseDto(
            model.key,
            model.name,
            model.spdxId,
            model.nodeId
        )
    }

    fun toModel(): RepoLicenseModel = RepoLicenseModel(
        key,
        name,
        spdxId,
        nodeId
    )
}