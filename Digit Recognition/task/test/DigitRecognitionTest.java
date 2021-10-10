import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testcase.TestCase;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

class Clue {
    String answer;
    Clue(int ans) {
        answer = Integer.toString(ans);
    }
}

public class DigitRecognitionTest extends StageTest<Clue> {

    static String filename = "testfile.txt";


    static String zero = "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t56\t105\t220\t254\t63\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t18\t166\t233\t253\t253\t253\t236\t209\t209\t209\t77\t18\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t84\t253\t253\t253\t253\t253\t254\t253\t253\t253\t253\t172\t8\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t57\t238\t253\t253\t253\t253\t253\t254\t253\t253\t253\t253\t253\t119\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t14\t238\t253\t253\t253\t253\t253\t253\t179\t196\t253\t253\t253\t253\t238\t12\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t33\t253\t253\t253\t253\t253\t248\t134\t0\t18\t83\t237\t253\t253\t253\t14\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t164\t253\t253\t253\t253\t253\t128\t0\t0\t0\t0\t57\t119\t214\t253\t94\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t57\t248\t253\t253\t253\t126\t14\t4\t0\t0\t0\t0\t0\t0\t179\t253\t248\t56\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t175\t253\t253\t240\t190\t28\t0\t0\t0\t0\t0\t0\t0\t0\t179\t253\t253\t173\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t209\t253\t253\t178\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t92\t253\t253\t208\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t211\t254\t254\t179\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t135\t255\t209\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t209\t253\t253\t90\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t134\t253\t208\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t209\t253\t253\t178\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t2\t142\t253\t208\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t209\t253\t253\t214\t35\t0\t0\t0\t0\t0\t0\t0\t0\t0\t30\t253\t253\t208\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t165\t253\t253\t253\t215\t36\t0\t0\t0\t0\t0\t0\t0\t0\t163\t253\t253\t164\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t18\t172\t253\t253\t253\t214\t127\t7\t0\t0\t0\t0\t0\t72\t232\t253\t171\t17\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t8\t182\t253\t253\t253\t253\t162\t56\t0\t0\t0\t64\t240\t253\t253\t14\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t7\t173\t253\t253\t253\t253\t245\t241\t239\t239\t246\t253\t225\t14\t1\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t18\t59\t138\t224\t253\t253\t254\t253\t253\t253\t240\t96\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t37\t104\t192\t255\t253\t253\t182\t73\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n";

    static String one = "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t134\t240\t4\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t158\t254\t63\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t158\t254\t63\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t158\t254\t63\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t158\t254\t63\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t159\t255\t63\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t158\t254\t63\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t158\t254\t63\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t158\t254\t63\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t183\t239\t4\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t239\t239\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t238\t238\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t52\t250\t173\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t84\t253\t138\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t142\t253\t80\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t143\t254\t80\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t142\t253\t80\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t142\t253\t80\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t142\t253\t125\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t99\t248\t110\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n";

    static String two = "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t14\t107\t226\t255\t254\t254\t254\t135\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t7\t75\t157\t228\t254\t254\t254\t254\t254\t254\t244\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t93\t254\t254\t254\t254\t189\t108\t67\t96\t247\t244\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t56\t251\t254\t234\t139\t20\t2\t0\t0\t18\t239\t244\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t49\t192\t211\t25\t0\t0\t0\t0\t0\t77\t254\t244\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t110\t254\t175\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t71\t251\t245\t45\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t154\t254\t130\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t102\t239\t229\t42\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t65\t243\t251\t108\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t64\t239\t254\t155\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t66\t240\t254\t192\t16\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t15\t239\t254\t240\t55\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t1\t129\t254\t253\t93\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t88\t254\t254\t171\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t53\t241\t254\t254\t243\t230\t230\t189\t106\t18\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t2\t170\t254\t254\t254\t254\t254\t254\t254\t254\t206\t25\t2\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t92\t254\t254\t254\t254\t254\t232\t186\t241\t254\t254\t254\t194\t77\t16\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t92\t254\t254\t242\t128\t44\t19\t0\t23\t58\t140\t234\t254\t254\t232\t81\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t23\t62\t62\t41\t0\t0\t0\t0\t0\t0\t0\t30\t157\t208\t250\t22\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n";


    static String three = "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t31\t8\t23\t156\t194\t186\t254\t254\t255\t163\t41\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t98\t254\t253\t253\t253\t253\t254\t253\t241\t69\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t68\t193\t179\t78\t78\t78\t186\t253\t253\t226\t18\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t5\t3\t0\t0\t0\t12\t113\t253\t253\t148\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t58\t253\t253\t155\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t37\t186\t254\t254\t133\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t4\t95\t201\t253\t253\t234\t31\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t19\t79\t109\t187\t253\t254\t253\t228\t111\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t201\t253\t253\t253\t253\t254\t253\t247\t219\t62\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t216\t253\t253\t162\t193\t254\t253\t253\t253\t253\t114\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t46\t183\t254\t254\t255\t84\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t12\t54\t218\t254\t233\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t98\t254\t233\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t7\t159\t254\t233\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t77\t253\t254\t195\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t121\t83\t0\t0\t0\t0\t0\t0\t49\t209\t254\t255\t39\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t195\t222\t26\t0\t0\t0\t13\t65\t196\t253\t253\t76\t6\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t128\t254\t217\t175\t86\t153\t224\t253\t253\t253\t192\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t45\t186\t253\t253\t253\t253\t254\t253\t240\t146\t5\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t14\t103\t155\t245\t253\t231\t147\t40\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n";

    static String four = "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t29\t220\t207\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t10\t125\t225\t254\t249\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t127\t255\t254\t246\t86\t0\t0\t0\t0\t0\t0\t93\t245\t149\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t164\t254\t249\t87\t0\t0\t0\t0\t0\t0\t93\t248\t254\t244\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t97\t245\t254\t175\t0\t0\t0\t0\t0\t0\t0\t182\t254\t254\t233\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t56\t234\t254\t204\t10\t0\t0\t0\t0\t0\t0\t9\t203\t254\t254\t111\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t190\t254\t254\t139\t0\t0\t0\t0\t0\t0\t6\t216\t254\t254\t222\t19\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t245\t254\t254\t229\t118\t63\t63\t63\t63\t123\t197\t254\t254\t249\t99\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t113\t253\t254\t254\t254\t254\t254\t254\t254\t254\t254\t254\t253\t101\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t73\t151\t217\t254\t254\t254\t254\t254\t254\t254\t254\t113\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t15\t58\t155\t155\t155\t194\t254\t254\t229\t15\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t117\t254\t254\t61\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t40\t242\t254\t224\t10\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t150\t254\t249\t103\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t4\t227\t254\t213\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t150\t254\t254\t68\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t214\t254\t187\t2\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t59\t249\t254\t151\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t10\t222\t254\t243\t40\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t3\t140\t254\t159\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n";


    static String five = "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t141\t253\t170\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t57\t56\t0\t0\t57\t168\t253\t251\t168\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t141\t253\t254\t253\t254\t253\t254\t253\t254\t253\t169\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t253\t251\t253\t251\t253\t251\t253\t251\t253\t251\t56\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t169\t255\t253\t254\t253\t254\t253\t254\t253\t114\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t168\t253\t251\t196\t83\t84\t83\t84\t83\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t169\t255\t253\t169\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t168\t253\t251\t168\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t141\t253\t254\t253\t85\t28\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t114\t253\t251\t253\t251\t253\t196\t169\t168\t114\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t85\t253\t254\t253\t254\t253\t254\t253\t254\t253\t254\t253\t57\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t85\t251\t253\t251\t253\t251\t253\t251\t253\t251\t253\t251\t225\t56\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t57\t168\t169\t168\t0\t0\t0\t0\t57\t168\t254\t253\t254\t253\t57\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t139\t251\t253\t251\t168\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t29\t197\t254\t253\t169\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t197\t251\t253\t251\t56\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t85\t253\t169\t0\t0\t0\t0\t0\t29\t197\t254\t253\t254\t196\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t85\t251\t225\t168\t169\t168\t169\t168\t197\t251\t253\t251\t196\t28\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t57\t168\t254\t253\t254\t253\t254\t253\t254\t253\t226\t168\t114\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t139\t251\t253\t251\t253\t251\t196\t83\t56\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n";


    static String six = "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t129\t255\t120\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t168\t247\t254\t192\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t57\t249\t254\t254\t85\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t31\t220\t254\t254\t125\t1\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t23\t214\t254\t249\t114\t13\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t28\t191\t254\t254\t109\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t32\t219\t254\t251\t142\t1\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t159\t254\t254\t188\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t69\t241\t254\t254\t79\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t240\t254\t254\t254\t242\t101\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t44\t249\t254\t243\t242\t254\t239\t63\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t46\t231\t254\t175\t25\t16\t156\t254\t240\t36\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t136\t254\t254\t86\t0\t0\t22\t254\t254\t52\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t216\t254\t208\t9\t0\t0\t15\t254\t254\t89\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t216\t254\t105\t0\t0\t0\t15\t254\t254\t52\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t216\t254\t120\t0\t0\t0\t117\t254\t254\t52\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t176\t254\t242\t73\t0\t40\t243\t254\t236\t32\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t9\t208\t254\t247\t163\t214\t254\t254\t148\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t21\t184\t254\t254\t254\t252\t191\t20\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t13\t141\t188\t235\t148\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n";

    static String seven = "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t175\t140\t17\t83\t41\t0\t0\t0\t0\t0\t132\t251\t49\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t99\t247\t254\t252\t254\t253\t238\t164\t154\t117\t201\t254\t254\t73\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t69\t246\t254\t254\t254\t191\t218\t254\t254\t254\t254\t254\t254\t187\t3\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t179\t254\t254\t173\t52\t2\t7\t85\t99\t99\t167\t254\t250\t55\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t67\t250\t254\t146\t2\t0\t0\t0\t0\t0\t0\t134\t254\t156\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t81\t254\t196\t16\t0\t0\t0\t0\t0\t0\t79\t250\t249\t56\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t8\t26\t11\t0\t0\t0\t0\t0\t0\t0\t136\t254\t155\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t19\t231\t249\t58\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t127\t254\t153\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t65\t232\t235\t89\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t168\t254\t125\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t77\t247\t214\t10\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t6\t174\t254\t133\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t125\t254\t228\t46\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t30\t235\t254\t93\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t144\t254\t216\t11\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t76\t247\t254\t125\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t146\t255\t234\t49\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t22\t238\t255\t120\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t120\t254\t237\t23\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n";

    static String eight = "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t114\t196\t254\t254\t160\t7\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t38\t170\t253\t249\t243\t165\t250\t205\t36\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t81\t214\t253\t195\t53\t0\t0\t169\t253\t173\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t184\t253\t209\t16\t0\t0\t0\t169\t253\t173\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t21\t232\t253\t158\t0\t0\t0\t70\t227\t253\t144\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t3\t191\t253\t245\t67\t0\t19\t219\t253\t229\t37\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t84\t249\t253\t220\t87\t228\t253\t230\t47\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t129\t253\t253\t253\t253\t235\t77\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t10\t171\t253\t253\t253\t63\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t83\t228\t253\t253\t253\t118\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t38\t226\t253\t190\t229\t253\t251\t70\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t8\t201\t253\t170\t2\t4\t231\t253\t228\t19\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t121\t253\t174\t10\t0\t0\t49\t236\t253\t75\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t51\t246\t197\t38\t0\t0\t0\t0\t214\t253\t128\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t2\t93\t253\t159\t0\t0\t0\t0\t0\t214\t253\t129\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t21\t253\t253\t69\t0\t0\t0\t0\t0\t214\t253\t90\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t21\t253\t253\t69\t0\t0\t0\t1\t122\t251\t244\t72\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t21\t253\t253\t69\t0\t34\t110\t191\t253\t253\t110\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t1\t121\t253\t247\t244\t248\t253\t253\t225\t119\t3\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t16\t195\t253\t253\t253\t234\t152\t20\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n";

    static String nine = "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t64\t163\t163\t247\t221\t247\t130\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t16\t139\t190\t249\t253\t254\t253\t253\t253\t253\t136\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t14\t196\t253\t253\t250\t216\t101\t36\t67\t253\t253\t135\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t98\t254\t253\t192\t69\t0\t0\t0\t108\t253\t207\t20\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t14\t232\t254\t233\t23\t0\t0\t0\t0\t159\t253\t72\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t19\t254\t255\t234\t49\t27\t0\t0\t94\t241\t254\t105\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t8\t173\t254\t253\t244\t235\t145\t145\t239\t253\t223\t31\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t185\t250\t253\t253\t253\t254\t253\t253\t99\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t50\t131\t144\t183\t254\t253\t185\t12\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t155\t254\t253\t127\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t38\t228\t255\t192\t13\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t8\t204\t253\t212\t14\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t159\t253\t253\t91\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t30\t233\t253\t207\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t210\t253\t253\t72\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t99\t254\t254\t209\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t247\t253\t253\t93\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t46\t254\t253\t221\t27\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t162\t254\t253\t55\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t148\t254\t233\t23\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
        "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n";

    @Override
    public List<TestCase<Clue>> generate() {
        List<TestCase<Clue>> tests = new ArrayList<>();

        int i = 0;
        for (String file : new String[]{zero, one, two, three, four, five, six, seven, eight, nine}) {
            tests.add(new TestCase<Clue>()
                .setAttach(new Clue(i))
                .setInput("3\n" + filename)
                .addFile(filename, file)
            );
            i++;
        }

        return tests;
    }

    @Override
    public CheckResult check(String reply, Clue clue) {
        List<String> lines = reply.lines().collect(Collectors.toList());
        String lastLine = lines.get(lines.size() - 1);

        if (lines.size() < 1) {
            return CheckResult.wrong("Looks like your output is empty!");
        }

        String[] lastLineWords = lastLine.split("\\s+");

        List<String> foundedNumbers = new ArrayList<>();
        for (String word : lastLineWords) {
            if (word.matches("[0-9]+")) {
                foundedNumbers.add(word);
            }
        }

        if (foundedNumbers.size() > 1) {
            String numbers = String.join(", ", foundedNumbers);
            return new CheckResult(false,
                "Last line contains several numbers, " +
                    "you should output only one number.\n" +
                    "Numbers found: " + numbers);
        }

        if (foundedNumbers.size() == 0) {
            return new CheckResult(false,
                "Last line in output " +
                    "doesn't contain any numbers.");
        }

        String founded = foundedNumbers.get(0);

        if (!founded.equals(clue.answer)) {
            return new CheckResult(false,
                "Last line contains number " + founded + " " +
                    "but expected to contain number " + clue.answer);
        }

        return CheckResult.correct();
    }
}
