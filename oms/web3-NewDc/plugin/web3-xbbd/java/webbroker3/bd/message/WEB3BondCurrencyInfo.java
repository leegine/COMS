head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.54.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondCurrencyInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ʉݏ��(WEB3BondCurrencyInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/05 ������ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�ʉݏ��)<BR>
 * �ʉݏ��<BR>
 * <BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3BondCurrencyInfo extends Message
{
    /**
     * (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h<BR>
     */
    public String currencyCode;
    
    /**
     * (�בփ��[�g)<BR>
     * �בփ��[�g<BR>
     */
    public String fxRate;
    
    /**
     * (�ʉݏ��)<BR>
     * �R���X�g���N�^<BR>
     */
    public WEB3BondCurrencyInfo() 
    {
     
    }
}
@
