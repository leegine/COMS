head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.39.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiTradingTimeManagement.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p������ԊǗ�(WEB3SrvRegiTradingTimeManagement.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 ���� (���u) �V�K�쐬
*/

package webbroker3.srvregi;

import java.sql.Timestamp;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (�T�[�r�X���p������ԊǗ�)<BR>
 * �T�[�r�X���p������ԊǗ��N���X<BR>
 *                                                                 
 * @@author ����
 * @@version 1.0
 */
public class WEB3SrvRegiTradingTimeManagement extends WEB3GentradeTradingTimeManagement 
{
    /**
    * ���O���[�e�B���e�B<BR>
    */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SrvRegiTradingTimeManagement.class);

    /**
     * @@roseuid 416F4A9C029F
     */
    public WEB3SrvRegiTradingTimeManagement() 
    {
     
    }
    
    /**
     * (is�c�Ɠ�)<BR>
     * �Ώۓ��t���c�Ɠ����ǂ������肷��B
     *
     *1) ������ԊǗ�.�c�Ɠ��敪()�ɂ��A�Ώۓ��t�̉c�Ɠ��敪���擾����B
     *
     *�@@[����]
     *�@@�@@���t�F����.�Ώۓ��t
     *
     *2) �擾�����c�Ɠ��敪���A��c�Ɠ��̏ꍇ��false��ԋp����B
     *�@@�@@����ȊO�̏ꍇ�́Atrue��ԋp����B
     * <BR>
     * @@param l_tsObjectTimestamp - (�Ώۓ��t)<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 411CA984013A
     */
    public static boolean isBizDate(Timestamp l_tsObjectTimestamp) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isBizDate(Timestamp)";
        
        log.entering(STR_METHOD_NAME);
        
        String l_strClassName = WEB3SrvRegiTradingTimeManagement.class.getName();
        
        if (l_tsObjectTimestamp == null)
        {
            log.debug(l_strClassName + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                l_strClassName + STR_METHOD_NAME);
        }

        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsObjectTimestamp)))
        {
            log.debug("��c�Ɠ��̏ꍇ�́Afalse��ԋp����B");
            return false;
        }
        else 
        {
            log.debug("�c�Ɠ��̏ꍇ�́Atrue��ԋp����B");
            return true;
        }
    }
}
@
