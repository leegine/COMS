head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginContractUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p�����������(WEB3MarginContractUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 ������ (���u) �V�K�쐬
*/

package webbroker3.equity.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * �i�M�p����������ׁj�B<br>
 * <br>
 * �M�p����������׃N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginContractUnit extends Message 
{
    
    /**
     * (ID)<BR>
     * �����h�c
     */
    public String id;
    
    /**
     * (����)
     */
    public Date openDate;
    
    /**
     * (���P��)
     */
    public String contractPrice;
    
    /**
     * (������)
     */
    public String contractQuantity;
    
    /**
     * (�����)
     */
    public String contractExecPrice;
    
    /**
     * (�]�����v)<BR>
     */
    public String appraisalProfitLoss;
    
    /**
     * (��������)<BR>
     * <BR>
     * �ꊇ���ς̏ꍇ�ݒ�<BR>
     */
    public String orderQuantity;
    
    /**
     * (���o������)<BR>
     * <BR>
     * ��肪����ꍇ�ݒ�<BR>
     */
    public String partContQuantity;
    
    /**
     * (���Ϗ���)<BR>
     * <BR>
     * �ꊇ���ς̏ꍇ�ݒ�<BR>
     */
    public String settlePriority;
    
    /**
     * (�M�p�����������)<BR>
     * �R���X�g���N�^�B
     * @@return webbroker3.margin.message.WEB3MarginContractUnit
     * @@roseuid 40B6DFDD02CE
     */
    public WEB3MarginContractUnit() 
    {
     
    }
}
@
