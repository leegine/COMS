head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.32.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SupplyDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �񋟋敪�@@�萔��`�C���^�t�F�C�X(WEB3SupplyDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 ���� (���u) �V�K�쐬
Revesion History : 2007/11/02 �h�C(���u) �T�[�r�X���p�d�l�ύX�Ǘ��䒠�E�c�a���C�A�E�gNo029
*/
package webbroker3.common.define;

/**
 * �񋟋敪�@@�萔��`�C���^�t�F�C�X�B
 *
 * @@author ����
 * @@version 1.0
 */
public interface WEB3SupplyDivDef
{
    /**
     * NULL�F�����t�����s��Ȃ��@@
     */
    public final static String NO_CONDITION_ATTACHED = "NULL";

    /**
     * 0�F�����񋟂̂݁i�����B�����ɖ����񋟁A���B�����ɖ��񋟁j
     */
    public final static String FREE_SUPPLY = "0";

    /**
     * 1�F�L���^�����񋟁i�����B�����ɖ����񋟁A���B�����ɗL���񋟁j�@@
     */
    public final static String CHARGE_OR_FREE_SUPPLY = "1";

    /**
     * 2�F�����񋟂̂�(�E�c�~��)
     */
    public final static String CHARGE_SUPPLY_UTUMIYA = "2";

    /**
     * 3�F�L���^������(�E�c�~��)
     */
    public final static String CHARGE_OR_FREE_SUPPLY__UTUMIYA = "3";
}@
