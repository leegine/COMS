head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.16.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �a��،�����(WEB3AioSecurityTransferUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/07 ���z (���u) �V�K�쐬   
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�a��،�����)<BR>
 * �a��،����׃N���X
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferUnit extends Message
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
     * (�����敪)<BR>
     * �����敪�i�����A���M�̏ꍇ�j<BR>
     * <BR>
     * 0�F ���<BR>
     * 1�F ����<BR>
     * <BR>
     * �������A�����ȊO�̏ꍇ�Anull
     */
    public String taxType;
    
    /**
     * (����)<BR>
     * �ۗL����
     */
    public String changePossQuantity;
    
    /**
     * (�]���z)<BR>
     * �]���z
     */
    public String marketValue;
    
    /**
     * (�a��敪)<BR>
     * �a��敪<BR>
     * <BR>
     * 1�F �ی�<BR>
     * 2�F ��p
     */
    public String depositDiv;
    
    /**
     * (�a��،�����)<BR>
     * �R���X�g���N�^
     * @@return webbroker3.aio.message.WEB3AioSecurityTransferUnit
     * @@roseuid 416D1BA80187
     */
    public WEB3AioSecurityTransferUnit() 
    {
     
    }
}
@
