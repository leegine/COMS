head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.26.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqAssetUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������ۗL�������(WEB3FeqAssetUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ���� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[  
*/

package webbroker3.feq.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�O�������ۗL�������)<BR>
 * �O�������ۗL�������N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqAssetUnit extends Message 
{
    
    /**
     * (�ۗL���YID)<BR>
     * �ۗL���YID<BR>
     */
    public String assetId;
    
    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     */
    public String marketCode;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode;
    
    /**
     * (���n�����R�[�h)<BR>
     * ���n�����R�[�h<BR>
     */
    public String localProductCode;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public String productName;
    
    /**
     * (��������敪)<BR>
     * ��������敪<BR>
     * <BR>
     * 0�F���<BR>
     * 1�F����<BR>
     */
    public String taxType;
    
    /**
     * (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h<BR>
     */
    public String currencyCode;
    
    /**
     * (���t�\����)<BR>
     * ���t�\����<BR>
     */
    public String sellPossQuantity;
    
    /**
     * (����������)<BR>
     * ����������<BR>
     */
    public String orderedQuantity;
    
    /**
     * (���t�\�t���O)<BR>
     * ���t�\�t���O<BR>
     * <BR>
     * true�F���t�\<BR>
     * false�F���t�s��<BR>
     */
    public boolean sellPossFlag;
    
    /**
     * (�O�������ۗL�������)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 42074EE801A3
     */
    public WEB3FeqAssetUnit() 
    {
     
    }
}
@
