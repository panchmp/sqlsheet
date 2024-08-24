# -*- coding: utf-8 -*-

# General options
needs_sphinx = '1.0'
add_function_parentheses = True

extensions = ['myst_parser', 'sphinx.ext.autodoc', 'sphinx.ext.autosectionlabel', 'sphinx.ext.extlinks', 'sphinx-prompt', 'sphinx_substitution_extensions', 'sphinx_inline_tabs', 'pygments.sphinxext', ]

issues_github_path = "panchmp/sqlsheet"

source_encoding = 'utf-8-sig'
#pygments_style = 'friendly'
show_sphinx = False
master_doc = 'index'
exclude_patterns = ['_themes', '_static/css']
logo_only = True

# HTML options
html_theme = "furo"
html_theme_path = ["_themes"]
html_short_title = "SQL Sheet"
htmlhelp_basename = "sqlsheet" + '-doc'
html_use_index = True
html_show_sourcelink = False
html_static_path = ['_static']
html_logo = '_images/logo-no-background.svg'
html_favicon = '_images/favicon.svg'
html_css_files = ['svg.css', 'floating_toc.css']
html_js_files = ['floating_toc.js',]

html_theme_options = {
    'path_to_docs': 'site/sphinx',
    'repository_url': 'https://github.com/panchmp/sqlsheet',
    'repository_branch': 'master',
    'use_issues_button': True,
    'use_download_button': True,
    'use_fullscreen_button': True,
    'use_repository_button': True,
}


