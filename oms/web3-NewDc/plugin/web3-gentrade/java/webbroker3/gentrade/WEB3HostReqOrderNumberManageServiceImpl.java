head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3HostReqOrderNumberManageServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name           : ���ʔԍ��̔ԃN���X(WEB3HostReqOrderNumberManageServiceImpl.java)
 Author Name         : Daiwa Institute of Research
 */
package webbroker3.gentrade;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.message.*;
import com.fitechlabs.xtrade.kernel.util.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import webbroker3.common.*;
import webbroker3.system.tune.affinity.*;
import webbroker3.util.*;

/**
 * �i�������ʔԍ��̔ԃN���X�j<BR>
 * <BR>
 * �������ʔԍ����̔Ԃ���T�[�r�X<BR>
 */
public class WEB3HostReqOrderNumberManageServiceImpl
    implements WEB3HostReqOrderNumberManageService
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3HostReqOrderNumberManageServiceImpl.class);

    private static final String NULL_NUMBER = "000000000";

    /**
     * (get�V�K���ʃR�[�h)<BR>
     * SONAR�ʒm�L���[�ɏ������ލۂɕK�v�Ȏ��ʃR�[�h���̔Ԃ���B<BR>
     * ���ʃR�[�h�̃R�[�h�̌n�͈ȉ��̒ʂ�B<BR>
     * �E�P�`�Q���ځFAP�ADB�̑g���̂Q������<BR>
     * �E�S�`�X���ځF�A��<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)�،���ЃR�[�h�B<BR>
     * @@param l_strBranchCode - (���X�R�[�h)���X�R�[�h�B<BR>
     * @@param l_productType - (�����^�C�v�j�����^�C�v�B<BR>
     * @@return java.lang.String
     * @@throws WEB3BaseException
     * @@roseuid 403496A00344
     */
    public String getNewNumber(
        String l_strInstitutionCode,
        String l_strBranchCode,
        ProductTypeEnum l_productType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getNewNumber(String, String, ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);

        Boolean l_isGetNumber =
            (Boolean) ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3OrderReqNumberHead2ManageService.NOT_GET_NUMBER_FLAG);
        if (Boolean.TRUE.equals(l_isGetNumber))
        {
            return NULL_NUMBER;
        }
        String l_strNewOrderRequestNumber = null;
        String head2 = getHead2OfNewNumber();
        String vTable = getVTableName(head2);
        try
        {
            if (head2 == null)
            {
                throw new WEB3BaseRuntimeException(new ErrorInfo(), STR_METHOD_NAME);
            }
            l_strNewOrderRequestNumber = head2 +
                WEB3StringTypeUtility.formatNumber(Processors.getNewPkValue(vTable), 7);

        }
        catch (Exception de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        log.exiting(STR_METHOD_NAME);
        return l_strNewOrderRequestNumber;
    }

    /**
     * ���ʃR�[�h��Q�����擾
     */
    private String getHead2OfNewNumber()
    {
        WEB3OrderReqNumberHead2ManageService l_svc = (
            WEB3OrderReqNumberHead2ManageService)
            Services.getService(WEB3OrderReqNumberHead2ManageService.class);
        return l_svc.getOrderReqNumberHead2();
    }

    /**
     * serial_number�̃��R�[�h�����擾
     */
    private String getVTableName(String head2OfNewNumber)
    {
        return "vtable_" + head2OfNewNumber;
    }
}
@
