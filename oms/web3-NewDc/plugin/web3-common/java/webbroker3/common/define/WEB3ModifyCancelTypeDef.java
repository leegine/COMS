head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.53.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ModifyCancelTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������E����敪�萔��`�N���X(WEB3ModifyCancelTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/24  �����@@(sinocom) �V�K�쐬
Revesion History : 2006/07/12 �h�C (���u)�y�敨�I�v�V�����z�d�l�ύX�E�c�a���C�A�E�gNo.043
*/
package webbroker3.common.define;

/**
 * ���������E����敪�萔��`�N���X
 *
 * @@author �����(sinocom)
 * @@version 1.0
 */
public interface WEB3ModifyCancelTypeDef
{

    /**
     * �����l<BR>
     */
    public final static String INITIAL_VALUE = "0";

    /**
     * �����<BR>
     */
    public final static String CANCELING = "1";
    
    /**
     * �ꕔ�������<BR>
     */
    public final static String PART_CANCELED = "2";
    
    /**
     * �S���������<BR>
     */
    public final static String CANCELED = "3";
    
    /**
     * ������s<BR>
     */
    public final static String CANCEL_ERROR = "4";
    
    /**
     * ������<BR>
     */
    public final static String CHANGING = "5";
    
    /**
     * �ꕔ��������<BR>
     */
    public final static String PARTIALLY_CHANGED = "6";                    
    
    /**
     * �S����������<BR>
     */
    public final static String CHANGED = "7";    
    
    /**
     * �������s<BR>
     */
    public final static String CHANGE_ERROR = "8";    
    
    /**
     * �G���[<BR>
     */
    public final static String ERROR = "9";            
    
    /**
     * A:W�w�l�����ؑ֒�<BR>
     */
    public final static String W_LIMIT_TRANSFERING = "A";
    
    /**
     * B:W�w�l�����ꕔ�ؑ֊���<BR>
     */
    public final static String W_LIMIT_PARTIALLY_TRANSFERED = "B";                    
    
    /**
     * C:W�w�l�����S���ؑ֊���<BR>
     */
    public final static String W_LIMIT_TRANSFERED = "C";    
    
    /**
     * D:W�w�l�����ؑ֎��s<BR>
     */
    public final static String W_LIMIT_TRANSFER_ERROR = "D";    
}
@
