head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.04.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoTradeCountUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����������(WEB3PvInfoTradeCountUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
                   2005/10/07 杊��](���u) �O������������ǉ�
*/
package webbroker3.pvinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (����������)<BR>
 * ����������N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3PvInfoTradeCountUnit extends Message 
{
    
    /**
     * (�����������)<BR>
     * �����������<BR>
     */
    public String equityTradeCount;
    
    /**
     * (�M�p�������)<BR>
     * �M�p�������<BR>
     */
    public String marginTradeCount;
    
    /**
     * (�敨�������)<BR>
     * �敨�������<BR>
     */
    public String futuresTradeCount;
    
    /**
     * (�I�v�V�����������)<BR>
     * �I�v�V�����������<BR>
     */
    public String optionsTradeCount;

    /**
     * (�O�����������)<BR>
     * �O�����������<BR>
     */
    public String foreignEquityTradeCount;

    /**
     * (����������)<BR>
     * �R���X�g���N�^�B<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoTradeCountUnit
     * @@roseuid 41455AFE01C3
     */
    public WEB3PvInfoTradeCountUnit() 
    {
     
    }
}
@
