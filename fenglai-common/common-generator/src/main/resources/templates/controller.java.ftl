package ${package.Controller};

import com.fenglai.common.web.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import ${package.Service}.${table.serviceName};

/**
 * @description: ${table.comment!} Controller
 *
 * @author ${author}
 * @date: ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("api/v1<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    @Autowired
    private ${table.serviceName} ${table.serviceName?uncap_first};

    /**
     * 列表查询
     */
    @GetMapping(value = "list")
    public R list(){
        return R.ok();
    }

    /**
     * 根据id查询详情
     */
    @GetMapping(value = "getById")
    public R getById(Long id){
        return R.ok();
    }

    /**
     * 新增数据
     */
    @PostMapping(value = "add")
    public R add(){
        return R.ok();
    }

    /**
     * 根据id删除
     */
    @GetMapping(value = "deleteById")
    public R delete(Long id){
        return R.ok();
    }

}
</#if>
