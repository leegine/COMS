head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.52.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSelectSettleInstitutionUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �I�����ϋ@@�֖���(WEB3AioSelectSettleInstitutionUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 ���� (���u) �V�K�쐬
                   2004/10/25 ���E(���u) ���r���[
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�I�����ϋ@@�֖���)<BR>
 * �I�����ϋ@@�֖��׃N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */
public class WEB3AioSelectSettleInstitutionUnit extends Message
{
    /**
     * (���ϋ@@��ID)
     */
    public String paySchemeId;
    
    /**
     * (���ϋ@@�ւ̖���)
     */
    public String paySchemeName;
    
    /**
     * (��t��)<BR>
     * ���ϋ@@�ւ̌����_�ł̎�t��<BR>
     * <BR>
     * 0�F ��~��<BR>
     * 1�F ��t��<BR>
     */
    public String receptionStatus;
    
    /**
     * (�R���X�g���N�^)
     * @@return webbroker3.aio.message.WEB3AioSelectSettleInstitutionUnit
     * @@roseuid 40E257400363
     */
    public WEB3AioSelectSettleInstitutionUnit() 
    {
     
    }
}
@
