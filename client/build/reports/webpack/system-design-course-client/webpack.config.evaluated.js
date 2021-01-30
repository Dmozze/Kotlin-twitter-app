{
  mode: 'production',
  resolve: {
    modules: [
      '/home/ubuntu/Kotlin-twitter-app/build/js/packages/system-design-course-client/kotlin-dce',
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
      '/home/ubuntu/Kotlin-twitter-app/build/js/packages/system-design-course-client/kotlin-dce/system-design-course-client.js'
    ]
  },
  output: {
    path: '/home/ubuntu/Kotlin-twitter-app/client/build/distributions',
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