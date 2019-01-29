head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.42.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPODemandHistoryUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�\�����𖾍׃��b�Z�[�W�f�[�^(WEB3IPODemandHistoryUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 ���� (���u) �V�K�쐬
Revesion History : 2005/01/05 ���(SRA) �c�Ή�>>>049
*/

package webbroker3.ipo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * IPO�\�����𖾍׃��b�Z�[�W�f�[�^�N���X
 *                                                                
 * @@author ����
 * @@version 1.0
 */
public class WEB3IPODemandHistoryUnit extends Message
{
    
    /**
     * �\������No
     */
    public String demandHistoryId;
    
    /**
     * ��t����
     */
    public Date receptionDate;
    
    /**
     * IPO�������e�敪<BR>
     * <BR>
     * �@@1�F�@@�\��<BR>
     * �@@2�F�@@����<BR>
     * �@@3�F�@@���
     */
    public String ipoProcessingType;
    
    /**
     * �\������
     */
    public String demandQuantity;
    
    /**
     * �\�����i�敪<BR>
     * �@@0�F���s<BR>
     * �@@1�F�w�l
     */
    public String demandPriceDiv;
    
    /**
     * �\�����i
     */
    public String demandPrice;
    
    /**
     * IPO��t���ʋ敪<BR>
     * <BR>
     * �@@1�F�@@�L��<BR>
     * �@@2�F�@@����
     */
    public String ipoAcceptedResultDiv;
    
    /**
     * �ŐV�L�������t���O<BR>
     * <BR>
     * true�F�@@�ŐV�L��<BR>
     * false�F�@@�ŐV�L���łȂ�
     */
    public boolean newHistoryFlag;
    
    /**
     * �\���p�P�ʋ敪<BR>
     * <BR>
     * �P�F �����i���j<BR>
     * �Q�F �����i���j
     */
    public String displayUnitDiv;
    
    /**
     * �������敪<BR>
     * <BR>
     * 1:�~<BR>
     * 2:��
     */
    public String temporaryConditionDiv;
       
    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40DC06380396
     */
    public WEB3IPODemandHistoryUnit() 
    {
     
    }
}
@
