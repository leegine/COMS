head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.07.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoTradePriceUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����������(WEB3PvInfoTradePriceUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/03/03 ���Ō�(���u) �V�K�쐬
*/
package webbroker3.pvinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (����������)<BR>
 * ����������N���X<BR>
 * @@author ���Ō�
 * @@version 1.00
 */
public class WEB3PvInfoTradePriceUnit extends Message 
{
    
    /**
     * (�����������)<BR>
     * �����������<BR>
     */
    public String equityTradePrice;
    
    /**
     * (�M�p����������)<BR>
     * �M�p����������<BR>
     */
    public String marginTradePrice;
    
    /**
     * (�敨����������)<BR>
     * �敨����������<BR>
     */
    public String futuresTradePrice;
    
    /**
     * (�I�v�V��������������)<BR>
     * �I�v�V��������������<BR>
     */
    public String optionsTradePrice;
    
    /**
     * (����������)<BR>
     * �R���X�g���N�^<BR>
     */
    public WEB3PvInfoTradePriceUnit()
    {
        
    }
}
@
