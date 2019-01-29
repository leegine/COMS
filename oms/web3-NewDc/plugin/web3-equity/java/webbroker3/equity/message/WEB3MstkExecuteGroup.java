head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkExecuteGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�����������Ɖ���P��(WEB3MstkExecuteGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 �d��(���u) �V�K�쐬
                   2005/01/05 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * �i�����~�j�����������Ɖ���P�ʁj�B<BR>
 * <BR>
 * �����~�j�����������Ɖ���P�ʃN���X
 * @@author �d��
 * @@version 1.0
 */
public class WEB3MstkExecuteGroup extends Message 
{
    
    /**
     * �iID�j�B<BR>
     * <BR>
     * �����h�c<BR>
     * ����m�F��ʂւ̑J�ڂɎg�p�B
     */
    public String id;
    
    /**
     * �i�����R�[�h�j�B
     */
    public String productCode;
    
    /**
     * �i�������j�B
     */
    public String productName;
    
    /**
     * �i�s��R�[�h�j�B<BR>
     * <BR>
     * 1�F���� 2�F��� 3�F���É� 6�F���� 8�F�D�y 9�FNNM 10�FJASDAQ
     */
    public String marketCode;
    
    /**
     * �i�����敪�j�B<BR>
     * <BR>
     * 1:���t�@@2:���t
     */
    public String dealingType;
    
    /**
     * �i���������j�B
     */
    public String orderQuantity;
    
    /**
     * �i���������j�B
     */
    public Date orderDate;
    
    /**
     * �i�������s���j�B
     */
    public Date orderExecuteDate;
    
    /**
     * �i�����j�B
     */
    public Date executionTimestamp;
    
    /**
     * �i��n���j�B
     */
    public Date deliveryDate;
    
    /**
     * �i��芔���j�B
     */
    public String execQuantity;
    
    /**
     * �i���P���j�B
     */
    public String execPrice;
    
    /**
     * �i��n����j�B
     */
    public String deliveryPrice;
    
    /**
     * �i�����󋵋敪�j�B
     */
    public String miOrderState;
    
    /**
     * �i����\�t���O�j�B<BR>
     * <BR>
     * true�F����\<BR>
     * false�F����s��<BR>
     */
    public boolean cancelFlag;
    
    /**
     * �i�����~�j����������薾�ׁj�B<BR>
     * <BR>
     * �f�t�H���g�R���X�g���N�^�B<BR>
     * @@roseuid 4167B04F008F
     */
    public WEB3MstkExecuteGroup() 
    {
     
    }
}
@
