var nodeDataArray;
var linkDataArray;

function getDBName() { 
	return 'schokofabrik_test';
}

function createEntities() {
	nodeDataArray = [
	  { key: 'auftrag',
		items: [
			{ name: 'fname', iskey: true, figure: 'Decision', color: 'red' },
			{ name: 'auftragsdat', iskey: false, figure: 'Cube1', color: 'blue' },
			{ name: 'status', iskey: false, figure: 'Cube1', color: 'blue' }
	  ] },

	  { key: 'bedienung',
		items: [
			{ name: 'mnr', iskey: true, figure: 'Decision', color: 'red' },
			{ name: 'pnr', iskey: true, figure: 'Decision', color: 'red' }
	  ] },

	  { key: 'bestellung',
		items: [
			{ name: 'fname', iskey: true, figure: 'Decision', color: 'red' },
			{ name: 'prodnr', iskey: true, figure: 'Decision', color: 'red' }
	  ] },

	  { key: 'firma',
		items: [
			{ name: 'fname', iskey: true, figure: 'Decision', color: 'red' },
			{ name: 'kontaktadr', iskey: false, figure: 'Cube1', color: 'blue' },
			{ name: 'telnr', iskey: false, figure: 'Cube1', color: 'blue' }
	  ] },

	  { key: 'herstellung',
		items: [
			{ name: 'prodnr', iskey: true, figure: 'Decision', color: 'red' },
			{ name: 'mnr', iskey: true, figure: 'Decision', color: 'red' }
	  ] },

	  { key: 'kuendigungsdatum',
		items: [
			{ name: 'pnr', iskey: true, figure: 'Decision', color: 'red' },
			{ name: 'kdat', iskey: false, figure: 'Cube1', color: 'blue' }
	  ] },

	  { key: 'kuenstler',
		items: [
			{ name: 'pnr', iskey: true, figure: 'Decision', color: 'red' },
			{ name: 'bekanntheitsgrad', iskey: false, figure: 'Cube1', color: 'blue' }
	  ] },

	  { key: 'kunstschau',
		items: [
			{ name: 'kname', iskey: true, figure: 'Decision', color: 'red' },
			{ name: 'kdatum', iskey: true, figure: 'Decision', color: 'red' },
			{ name: 'kland', iskey: false, figure: 'Cube1', color: 'blue' },
			{ name: 'kort', iskey: false, figure: 'Cube1', color: 'blue' },
			{ name: 'platz', iskey: false, figure: 'Cube1', color: 'blue' }
	  ] },

	  { key: 'kunstwerk',
		items: [
			{ name: 'prodnr', iskey: true, figure: 'Decision', color: 'red' },
			{ name: 'schaetzwert', iskey: false, figure: 'Cube1', color: 'blue' }
	  ] },

	  { key: 'lager',
		items: [
			{ name: 'lbez', iskey: true, figure: 'Decision', color: 'red' },
			{ name: 'flaeche', iskey: false, figure: 'Cube1', color: 'blue' }
	  ] },

	  { key: 'lagerung',
		items: [
			{ name: 'lbez', iskey: true, figure: 'Decision', color: 'red' },
			{ name: 'prodnr', iskey: true, figure: 'Decision', color: 'red' },
			{ name: 'menge', iskey: false, figure: 'Cube1', color: 'blue' }
	  ] },

	  { key: 'maschine',
		items: [
			{ name: 'mnr', iskey: true, figure: 'Decision', color: 'red' },
			{ name: 'mbesch', iskey: false, figure: 'Cube1', color: 'blue' }
	  ] },

	  { key: 'mitarbeiter',
		items: [
			{ name: 'pnr', iskey: true, figure: 'Decision', color: 'red' },
			{ name: 'edat', iskey: false, figure: 'Cube1', color: 'blue' },
			{ name: 'lbez', iskey: false, figure: 'Cube1', color: 'blue' }
	  ] },

	  { key: 'person',
		items: [
			{ name: 'pnr', iskey: true, figure: 'Decision', color: 'red' },
			{ name: 'nname', iskey: false, figure: 'Cube1', color: 'blue' },
			{ name: 'vname', iskey: false, figure: 'Cube1', color: 'blue' }
	  ] },

	  { key: 'praesentation',
		items: [
			{ name: 'kname', iskey: true, figure: 'Decision', color: 'red' },
			{ name: 'kdatum', iskey: true, figure: 'Decision', color: 'red' },
			{ name: 'pnr', iskey: true, figure: 'Decision', color: 'red' },
			{ name: 'prodnr', iskey: true, figure: 'Decision', color: 'red' }
	  ] },

	  { key: 'produkt',
		items: [
			{ name: 'prodnr', iskey: true, figure: 'Decision', color: 'red' },
			{ name: 'prodbez', iskey: false, figure: 'Cube1', color: 'blue' },
			{ name: 'gewicht', iskey: false, figure: 'Cube1', color: 'blue' }
	  ] },

	  { key: 'standardsortiment',
		items: [
			{ name: 'prodnr', iskey: true, figure: 'Decision', color: 'red' },
			{ name: 'vpreis', iskey: false, figure: 'Cube1', color: 'blue' },
			{ name: 'verpackungsart', iskey: false, figure: 'Cube1', color: 'blue' }
	  ] },

	];
	return nodeDataArray;
}

function createRelationships() {
	linkDataArray = [
	  { from: 'auftrag', to: 'firma', text: 'N', toText: '1' },
	  { from: 'bedienung', to: 'mitarbeiter', text: 'N', toText: '1' },
	  { from: 'bedienung', to: 'maschine', text: 'N', toText: '1' },
	  { from: 'bestellung', to: 'auftrag', text: 'N', toText: '1' },
	  { from: 'bestellung', to: 'produkt', text: 'N', toText: '1' },
	  { from: 'herstellung', to: 'maschine', text: 'N', toText: '1' },
	  { from: 'herstellung', to: 'produkt', text: 'N', toText: '1' },
	  { from: 'kuendigungsdatum', to: 'mitarbeiter', text: 'N', toText: '1' },
	  { from: 'kuenstler', to: 'person', text: 'N', toText: '1' },
	  { from: 'kunstwerk', to: 'produkt', text: 'N', toText: '1' },
	  { from: 'lagerung', to: 'lager', text: 'N', toText: '1' },
	  { from: 'lagerung', to: 'produkt', text: 'N', toText: '1' },
	  { from: 'mitarbeiter', to: 'person', text: 'N', toText: '1' },
	  { from: 'mitarbeiter', to: 'lager', text: 'N', toText: '1' },
	  { from: 'praesentation', to: '', text: 'N', toText: '1' },
	  { from: 'praesentation', to: 'kunstschau', text: 'N', toText: '1' },
	  { from: 'praesentation', to: 'kuenstler', text: 'N', toText: '1' },
	  { from: 'praesentation', to: 'kunstwerk', text: 'N', toText: '1' },
	  { from: 'standardsortiment', to: 'produkt', text: 'N', toText: '1' },
	  { from: '', to: '', text: '', toText: '' }
	];
return linkDataArray;
}