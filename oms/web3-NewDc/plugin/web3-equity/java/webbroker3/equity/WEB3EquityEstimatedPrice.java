head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityEstimatedPrice.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�Z����v�Z����(WEB3EquityEstimatedPrice.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/11/14 ������@@�V�K�쐬 �i���f���jNo.1026
*/

package webbroker3.equity;

/**
 * (�T�Z����v�Z����)<BR>
 *
 * @@author ������
 * @@version 1.0
 */
public interface WEB3EquityEstimatedPrice
{
    /**
     * (get�v�Z�P��) <BR>
     * �v�Z�P�����擾����B<BR>
     */
    public double getCalcUnitPrice();

    /**
     * (get�T�Z��n���)<BR>
     * �T�Z��n������擾����B<BR>
     */
    public double getEstimateDeliveryAmount();
}
@
