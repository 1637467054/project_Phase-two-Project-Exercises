/**
 * 数组过滤器            例如{{CHARGE|optionKV(section.charge)}}中CHARGE对应的就是list，section.charge对应的就是key
 * @param list          例如：[{key:"C",value:"收费"},{key:"F",value:"免费"}]
 * @param key           例如："C"
 * @returns {string}    例如：收费
 */
let optionKVArray=(list,key)=>{
    if (!list||!key){
        return "";
    }else {
        let result="";
        for (let i = 0; i < list.length; i++) {
            if (key===list[i]["key"]){
                result = list[i]["value"];
            }
        }
        return result;
    }
};
let optionKV=(object,key)=>{
    if (!object||!key){
        return "";
    }else {
        let result="";
        for (let enums in object) {
            console.log("com.course.generator.enums:"+enums+";\n"+"object[com.course.generator.enums]:"+object[enums]+";\n"+"object[com.course.generator.enums]['value']:"+object[enums]["value"]+";");
            if (key===object[enums]["key"]){
                result = object[enums]["value"];
            }
        }
        return result;
    }
};
export default {
    optionKV
}