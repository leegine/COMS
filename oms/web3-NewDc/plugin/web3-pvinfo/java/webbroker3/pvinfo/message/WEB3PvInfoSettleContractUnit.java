head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.05.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoSettleContractUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ϊ����ԋ߂̌��ʏ��(WEB3PvInfoSettleContractUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
*/
package webbroker3.pvinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (���ϊ����ԋ߂̌��ʏ��)<BR>
 * ���ϊ����ԋ߂̌��ʏ��N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3PvInfoSettleContractUnit extends Message 
{
    
    /**
     * (������1�c�Ɠ��O)<BR>
     * ������1�c�Ɠ��O<BR>
     */
    public Date beforeBizDate;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public String productName;
    
    /**
     * (���ϊ����ԋ߂̌��ʏ��)<BR>
     * �R���X�g���N�^�B<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoSettleContractUnit
     * @@roseuid 4147AA4100A8
     */
    public WEB3PvInfoSettleContractUnit() 
    {
     
    }
}
@
