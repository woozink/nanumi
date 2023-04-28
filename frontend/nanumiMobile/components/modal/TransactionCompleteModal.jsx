import React, {useState, useRef, useMemo} from 'react';
import {
  Modal,
  View,
  Dimensions,
  Text,
  Pressable,
  Animated,
  Easing,
  TouchableWithoutFeedback,
} from 'react-native';
import {COLORS, FONTS, SIZES} from '../../constants';
import {RectButton} from '../../ui/Button';
import {useModal} from '../../hooks/useModal';
import Icon from 'react-native-ionicons';

const {width, height} = Dimensions.get('window');

const STARS = 5;

const TransactionCompleteModal = () => {
  const {hideModal} = useModal();
  const [rating, setRating] = useState(5);
  const animation = useRef(new Animated.Value(1)).current;

  const handleRate = star => {
    setRating(star);
  };

  const animateScale = animation.interpolate({
    inputRange: [1, 1.5, 2],
    outputRange: [1, 1.4, 1],
  });

  const animateOpacity = animation.interpolate({
    inputRange: [1, 1.2, 2],
    outputRange: [1, 0.5, 1],
  });

  const animateWobble = animation.interpolate({
    inputRange: [1, 1.25, 1.75, 2],
    outputRange: ['0deg', '-3deg', '3deg', '0deg'],
  });

  const animationStyle = {
    transform: [{scale: animateScale}, {rotate: animateWobble}],
    opacity: animateOpacity,
  };

  const handleAnimate = () => {
    Animated.timing(animation, {
      toValue: 2,
      duration: 400,
      easing: Easing.ease,
      useNativeDriver: true,
    }).start(() => {
      animation.setValue(1);
    });
  };

  const stars = useMemo(() => {
    return Array.from({length: STARS}, (_, i) => (
      <Pressable
        key={i + 1}
        onPress={() => {
          handleRate(i + 1), handleAnimate();
        }}>
        <Animated.View style={i + 1 <= rating ? animationStyle : null}>
          <Icon
            name={i + 1 <= rating ? 'star' : 'star-outline'}
            color={COLORS.yellow}
            size={48}
            style={{marginHorizontal: 6}}
          />
        </Animated.View>
      </Pressable>
    ));
  }, [rating, handleAnimate]);

  return (
    <Modal visible={true} transparent={true}>
      <Pressable style={styles.modalContainer} onPress={hideModal}>
        <TouchableWithoutFeedback onPress={event => event.stopPropagation()}>
          <View style={styles.modal}>
            <View style={styles.transactionContainer}>
              <Text style={styles.text}>거래완료</Text>
            </View>
            <View style={{flexDirection: 'row'}}>{stars}</View>
            <View
              style={{
                flexDirection: 'row',
                justifyContent: 'space-around',
                alignItems: 'center',
              }}>
              <RectButton
                minWidth={96}
                fontSize={FONTS.font}
                backgroundColor={COLORS.primary}
                handlePress={hideModal}>
                다음에 할게요
              </RectButton>
              <RectButton
                minWidth={96}
                fontSize={FONTS.font}
                handlePress={hideModal}>
                완료
              </RectButton>
            </View>
          </View>
        </TouchableWithoutFeedback>
      </Pressable>
    </Modal>
  );
};
export default TransactionCompleteModal;

const styles = {
  closeIcon: {
    width: SIZES.extraLarge,
    height: SIZES.extraLarge,
    position: 'absolute',
    zIndex: 1,
    top: SIZES.base * 2,
    right: SIZES.base * 2,
  },

  modalContainer: {
    flex: 1,
    backgroundColor: 'rgba(0,0,0,0.5)',
    justifyContent: 'center',
    alignItems: 'center',
  },
  modal: {
    width: '80%',
    height: height / 3,
    backgroundColor: COLORS.white,
    borderRadius: SIZES.base,
    padding: SIZES.extraLarge,
    position: 'absolute',
    zIndex: 1,
    top: '50%',
    left: '50%',
    transform: [{translateX: -0.4 * width}, {translateY: -0.15 * height}],
    justifyContent: 'space-between',
  },
  transactionContainer: {
    justifyContent: 'center',
  },
  text: {
    fontFamily: FONTS.bold,
    color: COLORS.primary,
    fontSize: SIZES.large,
  },

  subText: {
    fontFamily: FONTS.medium,
    color: COLORS.primary,
    fontSize: SIZES.font,
  },
};
