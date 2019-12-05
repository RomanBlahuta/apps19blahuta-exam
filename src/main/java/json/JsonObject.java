package json;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private JsonPair[] jsonPairs;

    public JsonObject() {
        jsonPairs = new JsonPair[] {};
    }

    public JsonObject(JsonPair... jsonPairs) {
        this.jsonPairs = jsonPairs;
    }

    @Override
    public String toJson() {
        if (jsonPairs.length == 0) {
            return "{}";
        }
        String json = "{";
        for (JsonPair p: jsonPairs) {
            json += "'" + p.key + "': ";
            json += p.value.toJson();
            json += ", ";
        }
        return json.substring(0, json.length()-2) + "}";
    }

    public void add(JsonPair jsonPair) {
        Boolean flag = true;
        int i = 0;
        for (JsonPair p: jsonPairs) {
            if (p.key.equals(jsonPair.key)) {
                flag = false;
                break;
            }
            i++;
        }
        if (flag) {
            JsonPair[] newPairs = new JsonPair[jsonPairs.length + 1];
            System.arraycopy(jsonPairs, 0, newPairs, 0, jsonPairs.length);
            newPairs[newPairs.length - 1] = jsonPair;
            jsonPairs = newPairs;
        }
        else {
            jsonPairs[i] = new JsonPair(jsonPair.key, jsonPair.value);
        }
    }

    public Json find(String name) {
        for (JsonPair p: jsonPairs) {
            if (p.key.equals(name)) {
                return p.value;
            }
        }
        return null;
    }

    public JsonObject projection(String... names) {
        JsonObject res = new JsonObject();
        for (String name: names) {
            for (JsonPair p: jsonPairs) {
                if (name.equals(p.key)) {
                    res.add(p);
                }
            }
        }
        return res;
    }
}
