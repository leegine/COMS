head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.14.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferProductCodeNameUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ۗL��������(WEB3AioSecurityTransferProductCodeNameUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/07 ���z (���u) �V�K�쐬   
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�ۗL��������)<BR>
 * �ۗL�������׃N���X
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferProductCodeNameUnit extends Message
{    
    /**
     * (���i�^�C�v)<BR>
     * ���i�^�C�v<BR>
     * <BR>
     * 1�F ����<BR>
     * 2�F ��<BR>
     * 3�F �����M��<BR>
     */
    public String instrumentsType;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h
     */
    public String productCode;
    
    /**
     * (������)<BR>
     * ������
     */
    public String productName;
    
    /**
     * (�ۗL��������)<BR>
     * �R���X�g���N�^
     * @@roseuid 4163C9AD030F
     */
    public WEB3AioSecurityTransferProductCodeNameUnit() 
    {
      
    }
}
@
