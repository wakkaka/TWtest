var path = require('path')

module.exports = {
	entry: {
		app:path.join(__dirname, '/index.js')
	},

	output: {
		path: path.join(__dirname, '/'),
		filename: 'bundle.js',
		publicPath: '/'
	}
}