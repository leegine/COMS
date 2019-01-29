head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.41.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOLotDetailUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO���I�����ڍ�(WEB3IPOLotDetailUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/20 杊��] (���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�Ǘ���IPO���I�����ڍ�)<BR>
 * �Ǘ���IPO���I�����ڍ׃f�[�^�N���X�B
 *                                                                
 * @@author 杊��]
 * @@version 1.0
 */
public class WEB3IPOLotDetailUnit extends Message
{
    
    /**
     * (���I�V�[�P���X)<BR>
     * IPO���I�V�[�P���X�B<BR>
     */
    public String lotSequence;
    
    /**
     * ���X�R�[�h
     */
    public String branchCode;
    
    /**
     * ���I�������g����
     */
    public String lotAllotTotalQuantity;
    
    /**
     * (�����������)<BR>
     * ����������ʁi�P���[�v������j
     */
    public String allotLimitQuantity;
    
    /**
     * �����ςݐ���
     */
    public String allotFinLimitQuantity;
    
    /**
     * �����Ώیڋq��
     */
    public String allotTargetNumber;
    
    /**
     * (�����ő�l)<BR>
     * �P�ڋq�ւ̊����ő�l�B
     */
    public String allotMax;
    
    /**
     * (�����ŏ��l)<BR>
     * �P�ڋq�ւ̊����ŏ��l�B
     */
    public String allotMin;
    
    /**
     * �⌇�������g����
     */
    public String waitingAllotTotalQuantity;
    
    /**
     * (�⌇�����������)<BR>
     * �⌇����������ʁi�P���[�v������j�B
     */
    public String waitingAllotLimitQuantity;
    
    /**
     * �⌇�����ςݐ���
     */
    public String waitingAllotFinLimitQuantity;
    
    /**
     * �⌇�����Ώیڋq��
     */
    public String waitingAllotTargetNumber;
    
    /**
     * (�⌇�����ő�l)<BR>
     * �P�ڋq�ւ̕⌇�����ő�l�B
     */
    public String waitingAllotMax;
    
    /**
     * (�⌇�����ŏ��l)<BR>
     * �P�ڋq�ւ̕⌇�����ŏ��l�B
     */
    public String waitingAllotMin;
    
    /**
     * (��������)<BR>
     * ���I�m�菈�������B
     */
    public Date transactionDate;
    
    /**
     * (�������)<BR>
     * ���I������ԁB<BR>
     * <BR>
     * �P�F�m�菈����<BR>
     * �Q�F�m�芮��<BR>
     * �X�F�ُ�I��
     */
    public String transactionState;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40EE26B50009
     */
    public WEB3IPOLotDetailUnit() 
    {
     
    }
}
@
