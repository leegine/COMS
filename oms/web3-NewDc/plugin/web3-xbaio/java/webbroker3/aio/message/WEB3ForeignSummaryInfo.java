head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.54.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3ForeignSummaryInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�݃T�}�����(WEB3ForeignSummaryInfo)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/8/22 �Ԑi (���u) �V�K�쐬
*/
package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�O�݃T�}�����)<BR>
 * �O�݃T�}�����N���X<BR>
 *
 * @@author �Ԑi(���u)
 * @@version 1.0
 */

public class WEB3ForeignSummaryInfo extends Message
{

    /**
     * (�O�݃R�[�h)<BR>
     * �O�݃R�[�h<BR>
     */
    public String foreignCurrencyCode;

    /**
     * (��������)<BR>
     * ��������<BR>
     */
    public String cashinNumber;

    /**
     * (�������v���z)<BR>
     * �������v���z<BR>
     */
    public String cashinTotalPrice;


    /**
     * @@roseuid 44EBB20E0177
     */
    public WEB3ForeignSummaryInfo() 
    {
     
    }
    
    /**
     * (�O�݃T�}�����j<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �P�j�@@����:�ʉ݃R�[�h���Z�b�g����B<BR>
     * @@param l_strForeignCurrencyCode - (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h<BR>
     * @@roseuid 44E43FB7018C
     */
    public WEB3ForeignSummaryInfo(String l_strCurrencyCode) 
    {
        this.foreignCurrencyCode =  l_strCurrencyCode;
    }
    
}
@
