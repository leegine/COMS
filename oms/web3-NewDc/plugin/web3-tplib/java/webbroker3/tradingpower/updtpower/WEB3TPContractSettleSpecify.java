head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.00.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPContractSettleSpecify.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���������e�̕ԍώw����(WEB3TPContractSettleSpecify.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/08/02 �R�c�@@��i(FLJ) �V�K�쐬
 */
package webbroker3.tradingpower.updtpower;

import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (���������e�̕ԍώw����) <BR>
 * �]�͌v�Z���g�p���錻�������e�̕ԍώw�����\������B
 */
public class WEB3TPContractSettleSpecify
{

    /**
     * ����ID
     */
    public long contractId;

    /**
     * �ԍϐ���
     */
    public double quantity;

    /**
     * @@roseuid 4136B0440277
     */
    public WEB3TPContractSettleSpecify()
    {

    }

    /**
     * (get����ID)<BR>
     * <BR>
     * ����ID���擾����B<BR>
     * @@return long
     * @@roseuid 4100C81703E4
     */
    public long getContractId()
    {
        return contractId;
    }

    /**
     * (set����ID)<BR>
     * <BR>
     * ����ID���Z�b�g����B<BR>
     * @@param l_lngContractId - ����ID
     * @@roseuid 4100C818001B
     */
    public void setContractId(long l_lngContractId)
    {
        contractId = l_lngContractId;
    }

    /**
     * (get�ԍϐ���)<BR>
     * <BR>
     * �ԍϐ��ʂ��擾����B<BR>
     * @@return double
     * @@roseuid 4100CAD402A5
     */
    public double getQuantity()
    {
        return quantity;
    }

    /**
     * (set�ԍϐ���)<BR>
     * <BR>
     * �ԍϐ��ʂ��Z�b�g����B<BR>
     * @@param l_dblQuantity - �ԍϐ���
     * @@roseuid 4100CAD402C4
     */
    public void setQuantity(double l_dblQuantity)
    {
        quantity = l_dblQuantity;
    }

    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .append("contractId", contractId)
            .append("quantity", quantity)
            .toString();
    }

}
@
