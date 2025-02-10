import json

# source file @ data dragon ddragon\${patch}\data\en_US\runesReforged.json
# In R2 we save rune images in runes/{runeId}.png for both keystones and their slots
# When used MAKE SURE you use up to date files
with open("files/runesReforged.json", "r", encoding="utf-8") as file:
    data = json.load(file)

for item in data:
    tree_id = item.get("id", "N/A")
    tree_name = item.get("name", "N/A")
    print(f"Tree ID: {tree_id}, Name: {tree_name}")

    slots = item.get("slots", [])
    for slot_index, slot in enumerate(slots):
        if(slot_index == 0):
            print("  Keystones:")
        else:
            print(f"  Slot {slot_index}:")


        for rune in slot.get("runes", []):
            rune_id = rune.get("id", "N/A")
            rune_name = rune.get("name", "N/A")
            print(f"    Rune ID: {rune_id}, Name: {rune_name}")

    print("-" * 40)