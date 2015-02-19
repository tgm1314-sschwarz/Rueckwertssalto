function init() {
        if (window.goSamples) goSamples();  // init for these samples -- you don't need to call this
        var $ = go.GraphObject.make;  // for conciseness in defining templates

        ERM =
          $(go.Diagram, "ERM",  // must name or refer to the DIV HTML element
            {
              initialContentAlignment: go.Spot.Center,
              allowDelete: false,
              allowCopy: false,
              layout: $(go.ForceDirectedLayout),
              "undoManager.isEnabled": true
            });

        // define several shared Brushes
        var bluegrad = $(go.Brush, go.Brush.Linear, { 0: "rgb(150, 150, 250)", 0.5: "rgb(86, 86, 186)", 1: "rgb(86, 86, 186)" });
        var greengrad = $(go.Brush, go.Brush.Linear, { 0: "rgb(158, 209, 159)", 1: "rgb(67, 101, 56)" });
        var redgrad = $(go.Brush, go.Brush.Linear, { 0: "rgb(206, 106, 100)", 1: "rgb(180, 56, 50)" });
        var yellowgrad = $(go.Brush, go.Brush.Linear, { 0: "rgb(254, 221, 50)", 1: "rgb(254, 182, 50)" });
        var lightgrad = $(go.Brush, go.Brush.Linear, { 1: "#E6E6FA", 0: "#FFFAF0" });

        // the template for each attribute in a node's array of item data
        var itemTempl =
          $(go.Panel, "Horizontal",
            $(go.Shape,
              { desiredSize: new go.Size(10, 10) },
              new go.Binding("figure", "figure"),
              new go.Binding("fill", "color")),
            $(go.TextBlock,
              { stroke: "#333333",
                font: "bold 14px sans-serif" },
              new go.Binding("text", "name"))
          );

        // define the Node template, representing an entity
        ERM.nodeTemplate =
          $(go.Node, "Auto",  // the whole node panel
            { selectionAdorned: true,
              resizable: false,
              layoutConditions: go.Part.LayoutStandard & ~go.Part.LayoutNodeSized,
              fromSpot: go.Spot.AllSides,
              toSpot: go.Spot.AllSides,
              isShadowed: true,
              shadowColor: "#C5C1AA" },
            new go.Binding("location", "location").makeTwoWay(),
            // define the node's outer shape, which will surround the Table
            $(go.Shape, "Rectangle",
              { fill: lightgrad, stroke: "#756875", strokeWidth: 3 }),
            $(go.Panel, "Table",
              { margin: 8, stretch: go.GraphObject.Fill },
              $(go.RowColumnDefinition, { row: 0, sizing: go.RowColumnDefinition.None }),
              // the table header
              $(go.TextBlock,
                {
                  row: 0, alignment: go.Spot.Center,
                  margin: new go.Margin(0, 14, 0, 2),  // leave room for Button
                  font: "bold 16px sans-serif"
                },
                new go.Binding("text", "key")),

              // the collapse/expand button
              $("Button",
                {
                  row: 0, alignment: go.Spot.TopRight,
                  "ButtonBorder.stroke": null,
                  click: function(e, but) {
                    var list = but.part.findObject("LIST");
                    if (list !== null) {
                      list.diagram.startTransaction("collapse/expand");
                      list.visible = !list.visible;
                      var shape = but.findObject("SHAPE");
                      if (shape !== null) shape.figure = (list.visible ? "TriangleUp" : "TriangleDown");
                      list.diagram.commitTransaction("collapse/expand");
                    }
                  }
                },
                $(go.Shape, "TriangleUp",
                  { name: "SHAPE", width: 6, height: 4 })),

              // the list of Panels, each showing an attribute
              $(go.Panel, "Vertical",
                {
                  name: "LIST",
                  row: 1,
                  padding: 5,
                  alignment: go.Spot.TopLeft,
                  defaultAlignment: go.Spot.Left,
                  stretch: go.GraphObject.Horizontal,
                  itemTemplate: itemTempl
                },
                new go.Binding("itemArray", "items"))
            )  // end Table Panel
          );  // end Node

        // define the Link template, representing a relationship
        ERM.linkTemplate =
          $(go.Link,  // the whole link panel
            {
              selectionAdorned: true,
              layerName: "Foreground",
              reshapable: false,
              routing: go.Link.AvoidsNodes,
              corner: 5,
              curve: go.Link.JumpOver
            },
            $(go.Shape,  // the link shape
              { stroke: "#303B45", strokeWidth: 2.5 }),
            $(go.TextBlock,  // the "from" label
              {
                textAlign: "center",
                font: "bold 14px sans-serif",
                stroke: "#1967B3",
                segmentIndex: 0,
                segmentOffset: new go.Point(NaN, NaN),
                segmentOrientation: go.Link.OrientUpright
              },
              new go.Binding("text", "text")),
            $(go.TextBlock,  // the "to" label
              {
                textAlign: "center",
                font: "bold 14px sans-serif",
                stroke: "#1967B3",
                segmentIndex: -1,
                segmentOffset: new go.Point(NaN, NaN),
                segmentOrientation: go.Link.OrientUpright
              },
              new go.Binding("text", "toText"))
          );

        var nodeDataArray = createEntities();
        var linkDataArray = createRelationships();
        ERM.model = new go.GraphLinksModel(nodeDataArray, linkDataArray);
      }