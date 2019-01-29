/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : NotifyType�N���X(NotifyType.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/01 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.enums;

import com.fitechlabs.fin.intellioms.enums.Enum;

/**
 * �ʒm�o�H
 * 
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public final class NotifyType extends Enum
{

    /**
     * �ʒm�o�H�̐����l���`���������N���X
     *
     * @author Takuji Yamada (FLJ)
     * @version 1.0
     */
    public static final class IntValues
    {

        /**
         * �ʒm�o�H�F���[���G���W��
         */
        public static final int RULE_ENGINE = 0;

        /**
         * �ʒm�o�H�F�蓮����(�ڋq)
         */
        public static final int MANUAL_CUSTOMER = 1;

        /**
         * �ʒm�o�H�F�蓮����(�I�y���[�^)
         */
        public static final int MANUAL_OPERATOR = 2;

        /**
         * �ʒm�o�H�F�蓮����(�Ǘ���)
         */
        public static final int MANUAL_ADMIN = 3;

        /**
         * �ʒm�o�H�F�Ď�(�I�����C��)
         */
        public static final int OBSERVER_ONLINE = 4;
        
    }

    /**
     * �ʒm�o�H�F���[���G���W��
     */
    public static final NotifyType RULE_ENGINE = new NotifyType(
        IntValues.RULE_ENGINE,
        "RULE_ENGINE");

    /**
     * �ʒm�o�H�F�蓮����(�ڋq)
     */
    public static final NotifyType MANUAL_CUSTOMER = new NotifyType(
        IntValues.MANUAL_CUSTOMER,
        "MANUAL_CUSTOMER");

    /**
     * �ʒm�o�H�F�蓮����(�I�y���[�^)
     */
    public static final NotifyType MANUAL_OPERATOR = new NotifyType(
        IntValues.MANUAL_OPERATOR,
        "MANUAL_OPERATOR");

    /**
     * �ʒm�o�H�F�蓮����(�Ǘ���)
     */
    public static final NotifyType MANUAL_ADMIN = new NotifyType(
        IntValues.MANUAL_ADMIN,
        "MANUAL_ADMIN");

    /**
     * �ʒm�o�H�F�Ď�(�I�����C��)
     */
    public static final NotifyType OBSERVER_ONLINE = new NotifyType(
        IntValues.OBSERVER_ONLINE,
        "OBSERVER_ONLINE");

    /**
     * �R���X�g���N�^
     */
    private NotifyType(int l_intValue, String l_strValue)
    {
        super(l_intValue, l_strValue);
    }

}