head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.01.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3BVSBookValueSpecsUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �뉿�P�����׏��(WEB3BVSBookValueSpecsUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/08 �Ɍ��t(���u) �V�K�쐬
*/
package webbroker3.tradehistory.message;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�뉿�P�����׏��)<BR>
 * �뉿�P�����׏��N���X<BR>
 * 
 * @@author �Ɍ��t
 * @@version 1.0 
 */
public class WEB3BVSBookValueSpecsUnit extends Message 
{
    /**
     * (�뉿�P�����ׂh�c)<BR>
     * �뉿�P�����ׂh�c<BR>
     */
    public String bookValueSpecId = null;
    
    /**
     * (�뉿�P�����׃��R�[�h�敪)<BR>
     * �뉿�P�����׃��R�[�h�敪<BR>
     * <BR>
     * 1�F�@@�c�����R�[�h<BR>
     * 2�F�@@���׃��R�[�h<BR>
     */
    public String bookvalRecDiv = null;
    
    /**
     * (�v�Z��)<BR>
     * �v�Z��<BR>
     */
    public Date calcDate = null;
    
    /**
     * (��n��)<BR>
     * ��n��<BR>
     */
    public Date deliveryDate = null;
    
    /**
     * (����)<BR>
     * ����<BR>
     * <BR>
     * 1�F�@@��( or ���n or �o��)<BR>
     * 2�F�@@��( or ���� or ����)<BR>
     * ��L�ȊO�F�@@���̑�<BR>
     */
    public String buySellDiv = null;
    
    /**
     * (���)<BR>
     * ���<BR>
     * <BR>
     * ����`�l�ɂ��ẮA��ʒ�`��<BR>
     * �u�뉿�P�����׏Ɖ�(�뉿�P�����׏Ɖ�).xls�v<BR>
     * �Q��<BR>
     */
    public String tradeType = null;
    
    /**
     * (����)<BR>
     * ����<BR>
     */
    public String quantity = null;
    
    /**
     * (���P��)<BR>
     * ���P��<BR>
     */
    public String execPrice = null;
    
    /**
     * (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h<BR>
     * <BR>
     * A0�F�@@US�h��<BR>
     * A3�F�@@���`�h��<BR>
     * Z4�F�@@���[��<BR>
     * ��L�ȊO�F�@@�~<BR>
     */
    public String ccyCode = null;
    
    /**
     * (��n���z)<BR>
     * ��n���z<BR>
     */
    public String deliveryAmount = null;
    
    /**
     * (���v)<BR>
     * ���v<BR>
     */
    public String prolossAmount = null;
    
    /**
     * (�뉿�v��)<BR>
     * �뉿�v��<BR>
     */
    public String calcAmount = null;
    
    /**
     * (�뉿���z)<BR>
     * �뉿���z<BR>
     */
    public String bookAmount = null;
    
    /**
     * (�c����)<BR>
     * �c����<BR>
     */
    public String balQuantity = null;
    
    /**
     * (�뉿�P��)<BR>
     * �뉿�P��<BR>
     */
    public String bookPrice = null;
    
    /**
     * (���c�敪)<BR>
     * ���c�敪<BR>
     * <BR>
     * 0�F�@@�񉼎c��<BR>
     * 1�F�@@���c��<BR>
     */
    public String debitBalDiv = null;
    
    /**
     * (�뉿�P�����׏��)<BR>
     * �R���X�g���N�^<BR>
     * @@return webbroker3.plsbvs.message.WEB3BVSBookValueSpecsUnit
     * @@roseuid 416E6E94015F
     */
    public WEB3BVSBookValueSpecsUnit() 
    {
     
    }
}
@
