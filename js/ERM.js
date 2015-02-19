var nodeDataArray;
var linkDataArray;

function getDBName() { 
	return 'testdb_rws';
}

function createEntities() {
	nodeDataArray = [
	  { key: 'airlines',
		items: [
			{ name: 'id', iskey: true, figure: 'Decision', color: 'red' },
			{ name: 'name', iskey: false, figure: 'Cube1', color: 'blue' },
			{ name: 'country', iskey: false, figure: 'Cube1', color: 'blue' }
	  ] },

	  { key: 'airports',
		items: [
			{ name: 'airportcode', iskey: true, figure: 'Decision', color: 'red' },
			{ name: 'name', iskey: false, figure: 'Cube1', color: 'blue' },
			{ name: 'country', iskey: false, figure: 'Cube1', color: 'blue' },
			{ name: 'city', iskey: false, figure: 'Cube1', color: 'blue' }
	  ] },

	  { key: 'countries',
		items: [
			{ name: 'code', iskey: true, figure: 'Decision', color: 'red' },
			{ name: 'name', iskey: false, figure: 'Cube1', color: 'blue' }
	  ] },

	  { key: 'flights',
		items: [
			{ name: 'airline', iskey: true, figure: 'Decision', color: 'red' },
			{ name: 'flightnr', iskey: true, figure: 'Decision', color: 'red' },
			{ name: 'departure_time', iskey: false, figure: 'Cube1', color: 'blue' },
			{ name: 'departure_airport', iskey: false, figure: 'Cube1', color: 'blue' },
			{ name: 'destination_time', iskey: false, figure: 'Cube1', color: 'blue' },
			{ name: 'destination_airport', iskey: false, figure: 'Cube1', color: 'blue' },
			{ name: 'planetype', iskey: false, figure: 'Cube1', color: 'blue' }
	  ] },

	  { key: 'freightplanes',
		items: [
			{ name: 'id', iskey: true, figure: 'Decision', color: 'red' },
			{ name: 'maxcargo', iskey: false, figure: 'Cube1', color: 'blue' }
	  ] },

	  { key: 'passengerplanes',
		items: [
			{ name: 'id', iskey: true, figure: 'Decision', color: 'red' },
			{ name: 'maxseats', iskey: false, figure: 'Cube1', color: 'blue' },
			{ name: 'seatsperrow', iskey: false, figure: 'Cube1', color: 'blue' }
	  ] },

	  { key: 'passengers',
		items: [
			{ name: 'id', iskey: true, figure: 'Decision', color: 'red' },
			{ name: 'firstname', iskey: false, figure: 'Cube1', color: 'blue' },
			{ name: 'lastname', iskey: false, figure: 'Cube1', color: 'blue' },
			{ name: 'airline', iskey: false, figure: 'Cube1', color: 'blue' },
			{ name: 'flightnr', iskey: false, figure: 'Cube1', color: 'blue' }
	  ] },

	  { key: 'planefleet',
		items: [
			{ name: 'airline', iskey: true, figure: 'Decision', color: 'red' },
			{ name: 'plane', iskey: true, figure: 'Decision', color: 'red' },
			{ name: 'nr', iskey: true, figure: 'Decision', color: 'red' },
			{ name: 'bought', iskey: false, figure: 'Cube1', color: 'blue' },
			{ name: 'price', iskey: false, figure: 'Cube1', color: 'blue' }
	  ] },

	  { key: 'planes',
		items: [
			{ name: 'id', iskey: true, figure: 'Decision', color: 'red' },
			{ name: 'manufacturer', iskey: false, figure: 'Cube1', color: 'blue' },
			{ name: 'type', iskey: false, figure: 'Cube1', color: 'blue' },
			{ name: 'lengthoverall', iskey: false, figure: 'Cube1', color: 'blue' },
			{ name: 'span', iskey: false, figure: 'Cube1', color: 'blue' },
			{ name: 'maxspeed', iskey: false, figure: 'Cube1', color: 'blue' }
	  ] },

	  { key: 'tickets',
		items: [
			{ name: 'id', iskey: true, figure: 'Decision', color: 'red' },
			{ name: 'passenger', iskey: false, figure: 'Cube1', color: 'blue' },
			{ name: 'issued', iskey: false, figure: 'Cube1', color: 'blue' },
			{ name: 'rownr', iskey: false, figure: 'Cube1', color: 'blue' },
			{ name: 'seatposition', iskey: false, figure: 'Cube1', color: 'blue' },
			{ name: 'specialmenu', iskey: false, figure: 'Cube1', color: 'blue' }
	  ] },

	];
	return nodeDataArray;
}

function createRelationships() {
	linkDataArray = [
	  { from: 'flights', to: '', text: 'N', toText: '1' },
	  { from: 'flights', to: 'planefleet', text: 'N', toText: '1' },
	  { from: 'flights', to: '', text: 'N', toText: '1' },
	  { from: 'flights', to: 'airports', text: 'N', toText: '1' },
	  { from: 'freightplanes', to: 'planes', text: 'N', toText: '1' },
	  { from: 'passengerplanes', to: 'planes', text: 'N', toText: '1' },
	  { from: 'passengers', to: '', text: 'N', toText: '1' },
	  { from: 'passengers', to: 'flights', text: 'N', toText: '1' },
	  { from: 'planefleet', to: 'airlines', text: 'N', toText: '1' },
	  { from: 'planefleet', to: 'planes', text: 'N', toText: '1' },
	  { from: 'tickets', to: 'passengers', text: 'N', toText: '1' },
	  { from: '', to: '', text: '', toText: '' }
	];
return linkDataArray;
}