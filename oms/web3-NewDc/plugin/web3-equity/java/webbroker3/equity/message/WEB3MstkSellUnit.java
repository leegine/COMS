head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkSellUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�������t���׃N���X(WEB3MstkSellUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ���C�g (���u) �V�K�쐬
*/

package webbroker3.equity.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * �i�����~�j�������t���ׁj�B<BR>
 * <BR>
 * �����~�j�������t���׃N���X
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3MstkSellUnit extends Message 
{
    
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
     * �i�c�������j�B
     */
    public String balanceQuantity;
    
    /**
     * �i���t�\�����j�B
     */
    public String sellPossQuantity;
    
    /**
     * �i���t�����������j�B
     */
    public String buyOrderedQuantity;
    
    /**
     * �i���t�����������j�B
     */
    public String sellOrderedQuantity;
    
    /**
     * �i���t�\�t���O�j�B<BR>
     * <BR>
     * true�F���t�\�@@�@@false�F���t�s��
     */
    public boolean sellPossFlag;
    
    /**
     * �i�����~�j�������t���ׁj�B<BR>
     * �f�t�H���g�R���X�g���N�^
     */
    public WEB3MstkSellUnit() 
    {
     
    }
}
@
