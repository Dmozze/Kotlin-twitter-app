{
  mode: 'production',
  resolve: {
    modules: [
      '/home/dmozze/Desktop/url-shortener-demo/build/js/packages/system-design-course-client/kotlin-dce',
      'node_modules'
    ]
  },
  plugins: [
    ProgressPlugin {
      profile: false,
      handler: [Function: handler],
      modulesCount: 500,
      showEntries: false,
      showModules: true,
      showActiveModules: true
    },
    TeamCityErrorPlugin {}
  ],
  module: {
    rules: [
      {
        test: /\.js$/,
        use: [
          'source-map-loader'
        ],
        enforce: 'pre'
      }
    ]
  },
  entry: {
    main: [
      '/home/dmozze/Desktop/url-shortener-demo/build/js/packages/system-design-course-client/kotlin-dce/system-design-course-client.js'
    ]
  },
  output: {
    path: '/home/dmozze/Desktop/url-shortener-demo/client/build/distributions',
    filename: [Function: filename],
    library: 'client',
    libraryTarget: 'umd'
  },
  devtool: 'source-map',
  stats: {
    warningsFilter: [
      /Failed to parse source map/
    ],
    warnings: false,
    errors: false
  }
}