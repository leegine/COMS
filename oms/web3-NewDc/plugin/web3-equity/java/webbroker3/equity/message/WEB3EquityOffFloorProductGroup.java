head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOffFloorProductGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������t���������I�����X�|���X(WEB3EquityOffFloorProductGroup)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 �����a��(SRA) �V�K�쐬
*/
package webbroker3.equity.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * �i����O�����������ׁj�B<BR>
 * <BR>
 * ����O�����������ׁB
 * @@author �����a��(SRA) 
 * @@version 1.0
 */
public class WEB3EquityOffFloorProductGroup extends Message
{

    /**
     * �i�f�t�H���g�R���X�g���N�^�j�B
     */
    public WEB3EquityOffFloorProductGroup()
    {
    }

    /**
     * �i�����R�[�h�j�B<BR>
     * <BR>
     * �����R�[�h�B
     */
    public String productCode;
    /**
     * �i�������j�B<BR>
     * <BR>
     * �������B
     */
    public String productName;
    /**
     * �i�s��R�[�h�j�B<BR>
     * <BR>
     * �s��R�[�h�B
     */
    public String marketCode;
    /**
     * �i��t�J�n�����j�B<BR>
     * <BR>
     * ��t�J�n�����B
     */
    public Date orderStartDatetime;
    /**
     * �i��t�I�������j�B<BR>
     * <BR>
     * ��t�I�������B
     */
    public Date orderEndDatetime;
    /**
     * �i���{���O�c�Ɠ��I�l�j�B<BR>
     * <BR>
     * ���{���O�c�Ɠ��I�l�B
     */
    public String lastClosingPrice;
    /**
     * �i�������i�j�B<BR>
     * <BR>
     * �������i�B
     */
    public String offFloorOrderPrice;
    /**
     * �i�������j�B<BR>
     * <BR>
     * �������B�i���P�ʁj
     */
    public String discountRate;
    /**
     * �i�\����������j�B<BR>
     * <BR>
     * �\����������B<BR>
     * �i��l������̒����\�����̏���l�j
     */
    public String maxApplyQuantity;
    /**
     * �i�\���P�ʁj�B<BR>
     * <BR>
     * �\���P�ʁB
     */
    public String applyUnit;
    /**
     * �i���t�\�t���O�j�B<BR>
     * <BR>
     * ���t�\�t���O�B<BR>
     * �itrue�F���t�\�@@false�F���t�s�j
     */
    public boolean buyPossFlag;
}
@
