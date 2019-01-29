head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqDateUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���t�^�̃f�[�^�̃��[�e�B���e�B(WEB3FeqDateUtility)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/11 ���Ō� (���u) �V�K�쐬
*/
package webbroker3.feq.util;

import java.sql.Timestamp;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;

/**
 * ���t�^�̃f�[�^�̏������s���֐��������[�e�B���e�B�N���X�B<BR>
 * <BR>
 * 
 * @@author ���Ō�(���u)
 * @@version 1.0
 */
public class WEB3FeqDateUtility extends WEB3DateUtility
{
    /**
     * ���͂��ꂽ���t���O�������������̏ꍇ<BR>
     * true��ԋp����B<BR>
     * �ȊO�Afalse��ԋp����B<BR>
     *
     * @@param l_tsInputDate ���t
     * @@return ���͂��ꂽstr�͎w�肵���t�H�[�}�b�g�̓��t�Ȃ�A<BR>
     */
    public static boolean isFeqBizDate(Timestamp l_tsInputDate)
        throws WEB3BaseException
    {
        //�c�Ɠ��敪�擾
        String l_strCommonBizDateType = 
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsInputDate);
        //�C�O�̉c�Ɠ��敪�擾
        String l_strFeqBizDateType = 
            WEB3GentradeTradingTimeManagement.getFeqBizDateType(l_tsInputDate);
        
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strCommonBizDateType)
                || WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strFeqBizDateType))
        {
            return false;
        }
        else
        {
            return true;
        }
        
    }
    
}
@
