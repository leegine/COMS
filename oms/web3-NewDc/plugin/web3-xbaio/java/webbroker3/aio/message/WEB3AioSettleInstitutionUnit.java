head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.56.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSettleInstitutionUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��g���ϋ@@�֖���(WEB3AioSettleInstitutionUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 ���� (���u) �V�K�쐬
                   2004/10/27 ���E(���u) ���r���[
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (��g���ϋ@@�֖���)<BR>
 * ��g���ϋ@@�֖��׃N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */
public class WEB3AioSettleInstitutionUnit extends Message
{
    
    /**
     * (���ϋ@@�ւ�ID)
     */
    public String paySchemeId;
    
    /**
     * (���ϋ@@�ւ̖���)
     */
    public String paySchemeName;
    
    /**
     * (��g���ϋ@@�֖���)<BR>
     * �R���X�g���N�^
     * @@return webbroker3.aio.message.WEB3AioSettleInstitutionUnit
     * @@roseuid 40E51A76030F
     */
    public WEB3AioSettleInstitutionUnit() 
    {
     
    }
}
@
