export const withDefaultOptions = (filter = null) => {
    return {
        page: 0,
        size: 999999,
        sort: 'asc',
        filter: filter
    }
}

export const withOptions = (
    {
        page = 0,
        size = 5,
        sort = 'asc',
        filter = null
    }
) => {
    return {
        page: page,
        size: size,
        sort: sort,
        filter: filter,
    }
}

export const TOKEN = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI2ODU5ODU0NC1hYWRjLTQ2ZjgtODBjYS1lOTI0MWM3NzZjOGMsbG1hbyIsImlhdCI6MTYzMzgwNzkzOSwiZXhwIjoxNjM0NDEyNzM5fQ.PBy06PlyHx13yvSqCOLOniNVU-wfjwtDwvJ0s1OMFbgegFfPLdugPuwMRHdRhi97ig6bfvKA59bmEAvU0YKBNw'
